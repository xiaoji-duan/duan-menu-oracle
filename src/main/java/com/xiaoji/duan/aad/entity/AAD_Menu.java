package com.xiaoji.duan.aad.entity;

import java.io.Serializable;

public class AAD_Menu implements Serializable {

	private static final long serialVersionUID = 2737518049421586459L;

	private String unionId;
	private String subdomain;
	private Integer menuLevel;
	private Integer menuId;
	private Integer menuParentId;
	private String menuName;
	private String menuAction;
	private Integer menuOrder;
	private String menuPopupId;
	private Integer subMenus;

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getSubdomain() {
		return subdomain;
	}

	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer level) {
		this.menuLevel = level;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuParentId() {
		return menuParentId;
	}

	public void setMenuParentId(Integer menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuAction() {
		return menuAction;
	}

	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public Integer getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(Integer subMenus) {
		this.subMenus = subMenus;
	}

	public String getMenuPopupId() {
		return menuPopupId;
	}

	public void setMenuPopupId(String menuPopupId) {
		this.menuPopupId = menuPopupId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AAD_Menu [unionId=").append(unionId).append(", subdomain=").append(subdomain)
				.append(", menuLevel=").append(menuLevel).append(", menuId=").append(menuId).append(", menuParentId=")
				.append(menuParentId).append(", menuName=").append(menuName).append(", menuAction=").append(menuAction)
				.append(", menuOrder=").append(menuOrder).append(", menuPopupId=").append(menuPopupId)
				.append(", subMenus=").append(subMenus).append("]");
		return builder.toString();
	}


}
