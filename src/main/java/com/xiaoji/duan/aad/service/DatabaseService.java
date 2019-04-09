package com.xiaoji.duan.aad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoji.duan.aad.mapper.DatabaseMapper;
import com.xiaoji.duan.aad.service.db.AbstractSql;
import com.xiaoji.duan.aad.service.db.CreateTable;

@Service
public class DatabaseService {

	@Autowired
	private DatabaseMapper databaseMapper;
	
	public void initDatabase() {
		AbstractSql sql = new CreateTable();
		excute(sql.getDdl());
	}
	
	@Transactional
	public void excute(List<String> sqls) {
		for (String sql : sqls) {
			try {
				this.databaseMapper.execute(sql);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
