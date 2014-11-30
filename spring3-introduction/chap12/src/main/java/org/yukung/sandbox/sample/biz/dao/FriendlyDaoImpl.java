package org.yukung.sandbox.sample.biz.dao;

import java.sql.SQLException;

import org.yukung.sandbox.sample.biz.domain.Owner;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FriendlyDaoImpl implements FriendlyDao {

	@Override
	public Owner getOwnerDynamic(String id, String name) {
		return null;
	}

	@Override
	public void insertOwner(Owner owner) {
	}

	@Override
	public void deleteOwner(String id) {
	}

	public Owner getOwnerDynamic(SqlMapClient sqlMapClient, String id, String name) {
		Owner condition = new Owner();
		condition.setOwnerId(id);
		condition.setOwnerName(name);
		Owner result = null;
		try {
			result = (Owner) sqlMapClient.queryForObject("findOwnerDynamic", condition);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void insertOwner(SqlMapClient sqlMapClient, Owner owner) {
		try {
			sqlMapClient.insert("insertOwner", owner);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteOwner(SqlMapClient sqlMapClient, String id) {
		try {
			sqlMapClient.delete("deleteOwner", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
