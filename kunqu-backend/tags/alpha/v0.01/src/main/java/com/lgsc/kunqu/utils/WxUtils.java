package com.lgsc.kunqu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lgsc.kunqu.common.vo.AcessToken;
import com.lgsc.kunqu.common.vo.WXUserInfo;

@Component
public class WxUtils {

	public static Logger logger = LogManager.getLogger(WxUtils.class);

	public static String APP_SECRET;
	public static String APP_ID;
	public static String DOMAIN;
	public static String PORT;

	@Value("${app_id}")
	public void setAppId(String appId) {
		APP_ID = appId;
		logger.info("++++++++++-----------  APP_ID：" + APP_ID + "  -----------++++++++++");
	}

	@Value("${app_secret}")
	public void setAppSecret(String appSecret) {
		APP_SECRET = appSecret;
		logger.info("++++++++++-----------  APP_SECRET：" + APP_SECRET + "  -----------++++++++++");
	}

	@Value("${index_domain}")
	public void setDomain(String domain) {
		DOMAIN = domain;
		logger.info("++++++++++-----------  DOMAIN：" + DOMAIN + "  -----------++++++++++");
	}

	@Value("${index_port}")
	public void setPort(String port) {
		PORT = port;
		logger.info("++++++++++-----------  PORT：" + PORT + "  -----------++++++++++");
	}

	/**
	 * 获取用户信息
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public static void getUserInfo(String code, HttpServletResponse resp) throws IOException {
		String json = sendGet(
				"https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code"
						.replace("APPID", APP_ID).replace("APPSECRET", APP_SECRET).replace("CODE", code)); // 请求 access_token 和 openid
		logger.info("token与openid json：" + json);
		AcessToken t = JSON.parseObject(json, AcessToken.class);
		logger.info("openid：" + t.getOpenid() + ",access_token:" + t.getAccess_token());
		String userjson = sendGet(
				"https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"
						.replace("ACCESS_TOKEN", t.getAccess_token()).replace("OPENID", t.getOpenid())); // 请求用户信息
		logger.info(userjson);
		WXUserInfo user = JSON.parseObject(userjson, WXUserInfo.class);

		String nickname = URLEncoder.encode(user.getNickname(), "UTF-8");
		String headimgurl = URLEncoder.encode(user.getHeadimgurl(), "UTF-8");

		resp.sendRedirect("http://DOMAIN:PORT?nickname=NICKNAME&headimgurl=HEADIMGURL".replace("DOMAIN", DOMAIN)
				.replace("PORT", PORT).replace("NICKNAME", nickname).replace("HEADIMGURL", headimgurl));
	}

	/**
	 * 发送get请求
	 */
	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {

			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			// conn.setRequestProperty("accept", "*/*");
			// conn.setRequestProperty("connection", "Keep-Alive");
			// conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;
			// MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}