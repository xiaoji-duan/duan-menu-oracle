package com.xiaoji.duan.aad.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiaoji.duan.aad.entity.AAD_Menu;

@Mapper
public interface AAD_MenuMapper {

	@Select("SELECT UNIONID, SUBDOMAIN, MENU_ID, MENU_PARENT_ID, MENU_NAME, MENU_ACTION, MENU_POPUP_ID, MENU_ORDER FROM AAD_Menus WHERE UNIONID = #{unionId} OR (SUBDOMAIN = #{subdomain} AND MENU_ID = #{menuId})")
	@Results({
		@Result(column="UNIONID", property="unionId"),
		@Result(column="SUBDOMAIN", property="subdomain"),
		@Result(column="MENU_ID", property="menuId"),
		@Result(column="MENU_PARENT_ID", property="menuParentId"),
		@Result(column="MENU_NAME", property="menuName"),
		@Result(column="MENU_ACTION", property="menuAction"),
		@Result(column="MENU_POPUP_ID", property="menuPopupId"),
		@Result(column="MENU_ORDER", property="menuOrder")
	})
	public AAD_Menu findByPK(@Param("unionId") String uniondId, @Param("subdomain") String subdomain, @Param("menuId") Integer menuId);

	@Select("SELECT UNIONID, SUBDOMAIN, MENU_ID, MENU_PARENT_ID, MENU_NAME, MENU_ACTION, MENU_POPUP_ID, MENU_ORDER FROM AAD_Menus WHERE SUBDOMAIN = '${subdomain}'")
	@Results({
		@Result(column="UNIONID", property="unionId"),
		@Result(column="SUBDOMAIN", property="subdomain"),
		@Result(column="MENU_ID", property="menuId"),
		@Result(column="MENU_PARENT_ID", property="menuParentId"),
		@Result(column="MENU_NAME", property="menuName"),
		@Result(column="MENU_ACTION", property="menuAction"),
		@Result(column="MENU_POPUP_ID", property="menuPopupId"),
		@Result(column="MENU_ORDER", property="menuOrder")
	})
	public List<AAD_Menu> findAllBySubDomain(@Param("subdomain") String subdomain);

//	@Select("SELECT LEVEL MENU_LEVEL, SOLOVE_MENUS.UNIONID, SOLOVE_MENUS.SUBDOMAIN, SOLOVE_MENUS.MENU_ID, SOLOVE_MENUS.MENU_PARENT_ID, SOLOVE_MENUS.MENU_NAME, SOLOVE_MENUS.MENU_ACTION, SOLOVE_MENUS.MENU_ORDER "
//			+ "FROM ("
//			+ "(SELECT T_MENU.MENU_PARENT_ID, T_MENU.MENU_ID, T_MENU.MENU_NAME, T_MENU.MENU_ACTION, T_MENU.MENU_ORDER, CASE WHEN SUB_MENUS.SUBMENUS IS NULL THEN 0 ELSE SUB_MENUS.SUBMENUS END SUBMENUS "
//			+ "FROM AAD_Menu) T_MENU"
//			+ "LEFT JOIN ("
//			+ "SELECT DISTINCT MENU_PARENT_ID MENU_ID, COUNT(MENU_ID) OVER(PARTITION BY MENU_PARENT_ID) SUBMENUS "
//			+ "FROM AAD_Menu) SUB_MENUS "
//			+ "ON SUB_MENUS.MENU_ID = T_MENU.MENU_ID) SOLOVE_MENUS "
//			+ "START WITH SOLOVE_MENUS.MENU_PARENT_ID = 1 "
//			+ "CONNECT BY PRIOR SOLOVE_MENUS.MENU_ID = SOLOVE_MENUS.MENU_PARENT_ID "
//			+ "ORDER SIBLINGS BY SOLOVE_MENUS.MENU_ORDER")
	@Select("SELECT LEVEL MENU_LEVEL, SOLOVE_MENUS.* FROM (SELECT SD_MENUS.UNIONID, SD_MENUS.SUBDOMAIN, SD_MENUS.MENU_PARENT_ID, SD_MENUS.MENU_ID, SD_MENUS.MENU_NAME, SD_MENUS.MENU_ACTION, SD_MENUS.MENU_POPUP_ID, SD_MENUS.MENU_ORDER, CASE WHEN SUBMENUS.SUB_MENUS IS NULL THEN 0 ELSE SUBMENUS.SUB_MENUS END SUB_MENUS FROM (SELECT * FROM AAD_MENUS WHERE SUBDOMAIN = '${subdomain}') SD_MENUS LEFT JOIN (SELECT DISTINCT MENU_PARENT_ID MENU_ID, COUNT(MENU_ID) OVER(PARTITION BY MENU_PARENT_ID) SUB_MENUS FROM AAD_MENUS WHERE SUBDOMAIN = '${subdomain}') SUBMENUS ON SUBMENUS.MENU_ID = SD_MENUS.MENU_ID) SOLOVE_MENUS START WITH SOLOVE_MENUS.MENU_PARENT_ID = 1 CONNECT BY PRIOR SOLOVE_MENUS.MENU_ID = SOLOVE_MENUS.MENU_PARENT_ID ORDER SIBLINGS BY SOLOVE_MENUS.MENU_ORDER")
	@Results({
		@Result(column="UNIONID", property="unionId"),
		@Result(column="SUBDOMAIN", property="subdomain"),
		@Result(column="MENU_LEVEL", property="menuLevel"),
		@Result(column="MENU_ID", property="menuId"),
		@Result(column="MENU_PARENT_ID", property="menuParentId"),
		@Result(column="MENU_NAME", property="menuName"),
		@Result(column="MENU_ACTION", property="menuAction"),
		@Result(column="MENU_POPUP_ID", property="menuPopupId"),
		@Result(column="MENU_ORDER", property="menuOrder"),
		@Result(column="SUB_MENUS", property="subMenus")
	})
	public List<AAD_Menu> findBySubdomain(@Param("subdomain") String subdomain);
	
	@Insert("INSERT INTO AAD_Menus (UNIONID, SUBDOMAIN, MENU_ID, MENU_PARENT_ID, MENU_NAME, MENU_ACTION, MENU_ORDER) "
			+ "VALUES(#{unionId}, #{subdomain}, #{menuId}, #{menuParentId}, #{menuName}, #{menuAction}, #{menuOrder})")
	public int insert(AAD_Menu menu);
	
	@Update("UPDATE AAD_Menus SET MENU_ID = #{menuId}, MENU_PARENT_ID = #{menuParentId}, MENU_NAME = #{menuName}, MENU_ACTION = #{menuAction}, MENU_ORDER = #{menuOrder} WHERE UNIONID = #{unionId}")
	public int update(AAD_Menu menu);
	
	@Delete("DELETE FROM AAD_Menus WHERE UNIONID = #{unionId}")
	public int delete(@Param("unionId") String unionId);
}
