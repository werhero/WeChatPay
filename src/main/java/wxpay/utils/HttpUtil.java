package wxpay.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class HttpUtil {
	/**
	 * 发送post请求
	 * 
	 * @param url
	 *            请求地址
	 * @param outputEntity
	 *            发送内容
	 * @param isLoadCert
	 *            是否加载证书
	 */
	public static CloseableHttpResponse Post(String url, String outputEntity, boolean isLoadCert) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		// 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.setEntity(new StringEntity(outputEntity, "UTF-8"));
		if (isLoadCert) {
			// 加载含有证书的http请求
			return HttpClients.custom().setSSLSocketFactory(CertUtil.initCert()).build().execute(httpPost);
		} else {
			return HttpClients.custom().build().execute(httpPost);
		}
	}
}
