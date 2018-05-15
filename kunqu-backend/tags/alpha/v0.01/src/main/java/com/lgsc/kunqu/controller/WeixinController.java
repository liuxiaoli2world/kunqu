package com.lgsc.kunqu.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lgsc.kunqu.utils.WxUtils;

/**
 * 微信个人中心模块
 * 
 * @author zwl
 *
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {

	/**
	 * 获取微信用户信息
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/userinfo")
	public void getUserInfo(@RequestParam("code") String code, @RequestParam("state") String state,
			HttpServletResponse resp) throws IOException {
		WxUtils.getUserInfo(code,resp);
	}
}
