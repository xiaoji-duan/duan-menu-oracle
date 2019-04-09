package com.xiaoji.duan.aad.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.xiaoji.duan.aad.service.HttpService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class MainController {

    @Autowired
    private HttpService httpauth;
    
    @Value("${zuul.authorize.url}")
    private URL authurl;
    @Value("${zuul.authorize.path}")
    private String authpath;

    @Value("${zuul.groupuser.url}")
    private URL groupuserurl;
    @Value("${zuul.groupuser.path}")
    private String groupuserpath;

	@RequestMapping(value = {"/", "index"})
    public String index(HttpServletRequest req, Model model) {
		String token = "";
		String openid = "";
		
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				switch(cookie.getName()) {
				case "authorized_user":
    	            token = cookie.getValue();
    	            break;
				case "authorized_openid":
    	            openid = cookie.getValue();
    	            break;
				default:
					break;
				}
			}
		}
		
		JSONObject userinfo = new JSONObject();
		
		if (token != null && !"".equals(token) && openid != null && !"".equals(openid)) {
			Map<String, String[]> data = new HashMap<String, String[]>();
			Map<String, Object> res = httpauth.https(authurl + "/" + openid + "/info", data);
			
			System.out.println(res);
			if (res != null && res.get("data") != null && !((JSONObject) res.get("data")).isEmpty()) {
				userinfo = (JSONObject) res.get("data");
			}
		}
		
		if (openid != null && !"".equals(openid)) {
			String subdomain = "www";
			
			Map<String, String[]> data = new HashMap<String, String[]>();
			data.put("token", new String[]{token});
			data.put("openid", new String[]{openid});
			Map<String, Object> res = httpauth.https(groupuserurl + "/" + subdomain + "/username", data);

			System.out.println(res);
			if (res != null && res.get("data") != null && !((JSONObject) res.get("data")).isEmpty()) {
				userinfo = (JSONObject) res.get("data");
			}
		}
		
		if (userinfo.isEmpty()) {
			userinfo.put("name", "");
		}
		
		model.addAttribute("userinfo", userinfo);

      return "index";
    }
	
}
