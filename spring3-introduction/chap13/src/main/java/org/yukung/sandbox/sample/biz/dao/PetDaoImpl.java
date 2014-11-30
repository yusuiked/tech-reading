package org.yukung.sandbox.sample.biz.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yukung.sandbox.sample.biz.domain.Pet;

@Repository
public class PetDaoImpl implements PetDao {

	@Autowired
	private SqlSession session;

	@Override
	public Pet findPetById(Integer id) {
		return session.selectOne("org.yukung.sandbox.sample.biz.dao.PetMapper.selectPet", id);
	}
}
