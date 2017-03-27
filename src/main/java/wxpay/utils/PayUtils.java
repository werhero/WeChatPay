package wxpay.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import wxpay.config.Constant;
import wxpay.model.AuthToken;
import wxpay.model.PaySendData;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class PayUtils {
	/**
	 * 扩展xstream,使其支持name带有"_"的节点
	 */
	public static XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
	public static ObjectMapper jsonObjectMapper = new ObjectMapper();
	/**
	 * 根据code获取微信授权access_token
	 */
	public static AuthToken getTokenByAuthCode(String code) {
		AuthToken authToken = null;
		StringBuilder json = new StringBuilder();
		try {
			URL url = new URL(Constant.Authtoken_URL(code));
			URLConnection uc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
			// 将json字符串转成javaBean
			authToken = jsonToEntity(json.toString(), AuthToken.class);
		} catch (IOException ex) {
			System.out.println("获取access_token异常");
		}
		return authToken;
	}

	/**
	 * 获取微信签名
	 * 
	 * @param map
	 *            请求参数集合
	 * @return 微信请求签名串
	 */
	public static String getSign(SortedMap<String, Object> map) {
		StringBuffer buffer = new StringBuffer();
		Set<Map.Entry<String, Object>> set = map.entrySet();
		Iterator<Map.Entry<String, Object>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			String k = entry.getKey();
			Object v = entry.getValue();
			// 参数中sign、key不参与签名加密
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				buffer.append(k + "=" + v + "&");
			}
		}
		buffer.append("key=" + Constant.KEY);
		String sign = MD5.MD5Encode(buffer.toString()).toUpperCase();
		return sign;
	}

	/**
	 * 解析微信服务器发来的请求
	 * 
	 * @param inputStream
	 * @return 微信返回的参数集合
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(InputStream inputStream) {
		SortedMap<String, String> map = new TreeMap<String, String>();
		try {
			// 获取request输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素所有节点
			List<Element> elementList = root.elements();
			// 遍历所有子节点
			for (Element element : elementList) {
				map.put(element.getName(), element.getText());
			}
			// 释放资源
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("微信工具类:解析xml异常");
		}
		return map;
	}

	/**
	 * 请求参数转换成xml
	 * 
	 * @param data
	 * @return xml字符串
	 */
	public static String sendDataToXml(PaySendData data) {
		xStream.autodetectAnnotations(true);
		xStream.alias("xml", PaySendData.class);
		return xStream.toXML(data);
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return 当前时间戳字符串
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取指定长度的随机字符串
	 * 
	 * @param length
	 * @return 随机字符串
	 */
	public static String getRandomStr(int length) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 判断空字符串
	 */
	public static boolean isEmpty(String string) {
		if (string == null) {
			return true;
		}

		string = string.trim();
		if (string.equals("")) {
			return true;
		}

		return false;
	}

	/**
	 * Json字符转Java实体
	 * 
	 * @param jsonString
	 *            Json字符串
	 * @param entityType
	 *            实体类型
	 * @return default null，表示转换失败
	 */
	public static <T> T jsonToEntity(String jsonString, Class<T> entityType) {
		T entity = null;

		try {
			entity = jsonObjectMapper.readValue(jsonString, entityType);
		} catch (Exception e) {
			System.out.println("Json转换异常");;
		}

		return entity;
	}
}
