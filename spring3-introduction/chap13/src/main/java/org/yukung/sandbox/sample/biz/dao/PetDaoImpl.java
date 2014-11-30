package org.yukung.sandbox.sample.biz.dao;

import org.apache.ibatis.session.SqlSession;
import org.yukung.sandbox.sample.biz.domain.Pet;

public class PetDaoImpl implements PetDao {

	@Override
	public Pet findPetById(Integer id) {
		return null;
	}

	public Pet findPetById(SqlSession session, Integer id) {
		return session.selectOne("org.yukung.sandbox.sample.biz.dao.PetMapper.selectPet", id);
	}
}
