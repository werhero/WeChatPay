package wxpay.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class PaySendData {

	/**
	 * 公众账号ID[必须]
	 */
	@XStreamAlias("appid")
	private String appId;

	/**
	 * 商户号[必须]
	 */
	@XStreamAlias("mch_id")
	private String mch_id;

	/**
	 * 设备号[WEB]
	 */
	@XStreamAlias("device_info")
	private String device_info;

	/**
	 * 随机字符串[必须]
	 */
	@XStreamAlias("nonce_str")
	private String nonce_str;

	/**
	 * 签名[必须]
	 */
	@XStreamAlias("sign")
	private String sign;

	/**
	 * 商品描述[必须]
	 */
	@XStreamAlias("body")
	private String body;

	/**
	 * 商品详情
	 */
	@XStreamAlias("detail")
	private String detail;

	/**
	 * 附加数据
	 */
	@XStreamAlias("attach")
	private String attach;

	/**
	 * 商户订单号[必须]
	 */
	@XStreamAlias("out_trade_no")
	private String out_trade_no;

	/**
	 * 货币类型
	 */
	@XStreamAlias("fee_type")
	private String fee_type;

	/**
	 * 交易金额 [必须]
	 */
	@XStreamAlias("total_fee")
	private int total_fee;

	/**
	 * 交易类型 [必须]
	 */
	@XStreamAlias("trade_type")
	private String trade_type;

	/**
	 * 通知地址 [必须]
	 */
	@XStreamAlias("notify_url")
	private String notify_url;

	/**
	 * 终端Ip [必须]
	 */
	@XStreamAlias("spbill_create_ip")
	private String spbill_create_ip;

	/**
	 * 订单生成时间yyyyMMddHHmmss
	 */
	@XStreamAlias("time_start")
	private String time_start;

	/**
	 * 订单失效时间yyyyMMddHHmmss 间隔>5min
	 */
	@XStreamAlias("time_expire")
	private String time_expire;

	/**
	 * 用户标识 tradeType=JSAPI时必须
	 */
	@XStreamAlias("openid")
	private String openId;

	/**
	 * 商品标记
	 */
	@XStreamAlias("goods_tag")
	private String goods_tag;

	/** 指定支付方式 */
	@XStreamAlias("limit_pay")
	private String limit_pay;

	// ===============以下属性为申请退款参数===================

	/**
	 * 微信订单号 [必须] 与商户退款单号二选一
	 */
	@XStreamAlias("transaction_id")
	private String transaction_id;

	/**
	 * 商户退款单号 [必须] 与微信单号二选一
	 */
	@XStreamAlias("out_refund_no")
	private String out_refund_no;

	/**
	 * 退款金额 [必须]
	 */
	@XStreamAlias("refund_fee")
	private Integer refund_fee;

	/**
	 * 退款货币种类
	 */
	@XStreamAlias("refund_fee_type")
	private String refund_fee_type;

	/**
	 * 操作员账号:默认为商户号 [必须]
	 */
	@XStreamAlias("op_user_id")
	private String op_user_id;

	public PaySendData() {
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public Integer getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getRefund_fee_type() {
		return refund_fee_type;
	}

	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}

}