package wxpay.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;

import wxpay.config.Constant;

@SuppressWarnings("deprecation")
public class CertUtil {

	/**
	 * 加载证书
	 */
	public static SSLConnectionSocketFactory initCert() throws Exception {
		FileInputStream instream = null;
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		instream = new FileInputStream(new File(Constant.CERT_PATH));
		keyStore.load(instream, Constant.MCH_ID.toCharArray());
		
		if (null != instream) {
			instream.close();
		}

		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Constant.MCH_ID.toCharArray()).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

		return sslsf;
	}
}
