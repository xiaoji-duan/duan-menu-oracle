package com.xiaoji.duan.aad.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xiaoji.duan.aad.entity.AAD_Menu;
import com.xiaoji.duan.aad.mapper.AAD_MenuMapper;

@Service
public class AADService {

	private final AAD_MenuMapper menuMapper;
	
	public AADService(AAD_MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}
	
	public List<AAD_Menu> getSubdomainMenus(String subdomain) {
		return this.menuMapper.findBySubdomain(subdomain);
	}
	
	public List<AAD_Menu> getMenusList(String subdomain) {
		return this.menuMapper.findAllBySubDomain(subdomain);
	}

	public String deleteMenu(String unionId) {
		int deleted = this.menuMapper.delete(unionId);
		
		if (deleted > 0) {
			return unionId;
		} else {
			return "";
		}
	}
	
	public String saveMenu(AAD_Menu menu) {
		
		AAD_Menu exist = this.menuMapper.findByPK(menu.getUnionId(), menu.getSubdomain(), menu.getMenuId());
		
		if (exist == null) {
			String unionId = UUID.randomUUID().toString();
			menu.setUnionId(unionId);
			
			if (this.menuMapper.insert(menu) > 0) {
				return unionId;
			} else {
				return null;
			}
		} else {
			
			menu.setUnionId(exist.getUnionId());
			
			if (this.menuMapper.update(menu) > 0) {
				return exist.getUnionId();
			} else {
				return null;
			}
		}
		
	}
}
