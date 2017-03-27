package wxpay.ctrl;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wxpay.config.Constant;
import wxpay.model.AuthToken;
import wxpay.service.PayService;
import wxpay.utils.PayUtils;

@Controller
@RequestMapping(value = "/m/weChat")
public class WeChatOrderController {

	@Autowired
	private PayService payService;

	/**
	 * 统一下单
	 */
	@RequestMapping(value = "/unifiedOrder")
	public String unifiedOrder(HttpServletRequest request, Model model) {
		// 用户同意授权，获得的code
		String code = request.getParameter("code");
		// 通过code获取网页授权access_token
		AuthToken authToken = PayUtils.getTokenByAuthCode(code);
		// 调用统一下单service
		String prepayId = payService.unifiedOrder(authToken, request.getRemoteAddr());
		if (!PayUtils.isEmpty(prepayId)) {
			String timeStamp = PayUtils.getTimeStamp();// 当前时间戳
			String nonceStr = PayUtils.getRandomStr(20);// 不长于32位的随机字符串

			SortedMap<String, Object> signMap = new TreeMap<String, Object>();// 自然升序map
			signMap.put("appId", Constant.APP_ID);
			signMap.put("package", prepayId);
			signMap.put("timeStamp", timeStamp);
			signMap.put("nonceStr", nonceStr);
			signMap.put("signType", "MD5");

			model.addAttribute("appId", Constant.APP_ID);
			model.addAttribute("timeStamp", timeStamp);
			model.addAttribute("nonceStr", nonceStr);
			model.addAttribute("prepayId", prepayId);
			model.addAttribute("paySign", PayUtils.getSign(signMap));// 获取签名
		} else {
			System.out.println("微信统一下单失败,订单编号:失败原因");
			return "redirect:/";// 支付下单失败，重定向至订单列表
		}
		// 将支付需要参数返回至页面，采用h5方式调用支付接口
		return "h5Pay";
	}

	/**
	 * 申请退款
	 */
	@RequestMapping(value = "/refund")
	public String refund(HttpServletRequest request, Model model) {
		// 调用统一下单service
		payService.refund();
		return "h5Pay";
	}
}