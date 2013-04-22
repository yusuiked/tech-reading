package org.yukung.practice.chap7;

import java.io.Serializable;

public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private Employee leader;

	public Department(String name, Employee leader) {
		this.name = name;
		this.leader = leader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getEmployee() {
		return leader;
	}

	public void setEmployee(Employee leader) {
		this.leader = leader;
	}

}
