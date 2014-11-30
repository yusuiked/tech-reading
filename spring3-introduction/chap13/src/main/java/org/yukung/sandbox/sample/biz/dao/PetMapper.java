package org.yukung.sandbox.sample.biz.dao;

import org.yukung.sandbox.sample.biz.domain.Pet;

@MyMapper
public interface PetMapper {

	Pet selectPet(int id);
}
