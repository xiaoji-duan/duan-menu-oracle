package com.xiaoji.duan.aad.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoji.duan.aad.entity.AAD_Menu;
import com.xiaoji.duan.aad.service.AADService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/menus")
public class ApiController {

	@Autowired
	private AADService aadService;
	
	@RequestMapping("{subdomain}/currentmenus")
	public Map<String, Object> getCurrentMenus(@PathVariable String subdomain) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("code", "");
		rslt.put("message", "");
		
		Object data = this.aadService.getSubdomainMenus(subdomain);
		
		rslt.put("data", data);
		
		return rslt;
	}
	
	@RequestMapping("{subdomain}/list")
	public Map<String, Object> listMenu(@PathVariable String subdomain) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("code", "");
		rslt.put("message", "");

		Object data = this.aadService.getMenusList(subdomain);
		
		rslt.put("data", data);

		return rslt;
	}

	@RequestMapping("{subdomain}/{unionId}/delete")
	public Map<String, Object> deleteMenu(@PathVariable String subdomain, @PathVariable String unionId) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("code", "");
		rslt.put("message", "");

		Object data = new HashMap<String, Object>();

		String deletedUnionId = this.aadService.deleteMenu(unionId);
		
		if (deletedUnionId != null && !"".equals(deletedUnionId)) {
			((Map) data).put("unionId", deletedUnionId);
		} else {
			rslt.put("code", "AAD_100002");
			rslt.put("message", "菜单(" + unionId + ")删除失败.");
		}
		
		rslt.put("data", data);

		return rslt;
	}

	@RequestMapping("{subdomain}/save")
	public Map<String, Object> addMenu(@PathVariable String subdomain, @RequestBody(required = false) AAD_Menu menu) {
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("code", "");
		rslt.put("message", "");

		Object data = new HashMap<String, Object>();
		
		if (menu != null) {
			System.out.println(menu);
			String unionId = this.aadService.saveMenu(menu);
			
			if (unionId != null) {
				((Map) data).put("unionId", unionId);
			}
		} else {
			rslt.put("code", "AAD_100001");
			rslt.put("message", "菜单信息未填写,保存失败.");
		}
		
		rslt.put("data", data);

		return rslt;
	}
}
