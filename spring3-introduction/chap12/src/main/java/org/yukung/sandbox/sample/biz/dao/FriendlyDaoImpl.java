package org.yukung.sandbox.sample.biz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import org.yukung.sandbox.sample.biz.domain.Owner;

@Repository
public class FriendlyDaoImpl implements FriendlyDao {

	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	@Override
	public Owner getOwnerDynamic(String id, String name) {
		Owner owner = new Owner();
		owner.setOwnerId(id);
		owner.setOwnerName(name);
		return (Owner) sqlMapClientTemplate.queryForObject("findOwnerDynamic", owner);
	}

	@Override
	public void insertOwner(Owner owner) {
		sqlMapClientTemplate.insert("insertOwner", owner);
	}

	@Override
	public void deleteOwner(String id) {
		sqlMapClientTemplate.delete("deleteOwner", id);
	}
}
