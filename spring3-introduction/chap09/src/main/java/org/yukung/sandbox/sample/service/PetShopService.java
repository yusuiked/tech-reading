package org.yukung.sandbox.sample.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.yukung.sandbox.sample.entity.Pet;

@Service
public class PetShopService {

	public List<Pet> getAllPet() {
		List<Pet> pets = new ArrayList<Pet>();
		for (int i = 0; i < 5; i++) {
			Pet pet = new Pet();
			pet.setPetName("ポチ" + i);
			pet.setOwnerName("owner" + i);
			pet.setPrice(1000 * i);
			pet.setBirthDate(new Date());
			pets.add(pet);
		}
		return pets;
	}
}
