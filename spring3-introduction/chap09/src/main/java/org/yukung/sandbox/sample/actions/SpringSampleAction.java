package org.yukung.sandbox.sample.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.yukung.sandbox.sample.entity.Pet;
import org.yukung.sandbox.sample.service.PetShopService;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
public class SpringSampleAction extends ActionSupport {

	@Autowired
	private PetShopService petShopService;

	private List<Pet> pets;

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	@Action(value="spring", results={@Result(name="success", location="/WEB-INF/content/petList.jsp")})
	public String execute() {
		pets = petShopService.getAllPet();
		return SUCCESS;
	}

}
