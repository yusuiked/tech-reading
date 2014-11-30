package org.yukung.sandbox.sample.biz.dao;

import org.yukung.sandbox.sample.biz.domain.Pet;

public interface PetDao {

	Pet findPetById(Integer id);
}
