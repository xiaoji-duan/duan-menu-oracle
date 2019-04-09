package com.xiaoji.duan.aad.service.db;

public class CreateTable extends AbstractSql {

	public CreateTable() {
		initDdl();
	}

	private void initDdl() {
		ddl.add("" +
				"CREATE TABLE aad_menus (" +
				"		  \"UNIONID\" varchar2(36) NOT NULL," +
				"		  \"SUBDOMAIN\" varchar2(64) NOT NULL," +
				"		  \"MENU_ID\" number(8) NOT NULL," +
				"		  \"MENU_PARENT_ID\" number(8) NOT NULL," +
				"		  \"MENU_NAME\" varchar2(64) NOT NULL," +
				"		  \"MENU_ACTION\" varchar2(1024) NOT NULL," +
				"		  \"MENU_POPUP_ID\" varchar2(64) DEFAULT ''," +
				"		  \"MENU_ORDER\" number(8) DEFAULT 0," +
				"		  PRIMARY KEY (\"UNIONID\")" +
				"		)" +
				"");

	}
}
