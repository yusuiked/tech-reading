package org.yukung.sandbox.sample.biz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yukung.sandbox.sample.biz.domain.Pet;

@Repository
public class PetDaoMapperImpl implements PetDao {

	@Autowired
	private PetMapper petMapper;

	@Override
	public Pet findPetById(Integer id) {
		return petMapper.selectPet(id);
	}

}
