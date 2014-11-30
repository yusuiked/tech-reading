package org.yukung.sandbox.sample.biz.domain;

import java.util.ArrayList;
import java.util.List;

public class Owner {
	
	private String ownerName;
	
	private List<Pet> pets = new ArrayList<>();

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	
}
