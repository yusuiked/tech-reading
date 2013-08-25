package org.domain.seambook_userkanri.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.jboss.seam.annotations.Name;

@Name("user")
@Entity
public class User implements Serializable {
	@Id
	private String id;

	private String name;

	private String yakushoku;

	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYakushoku() {
		return this.yakushoku;
	}

	public void setYakushoku(String yakushoku) {
		this.yakushoku = yakushoku;
	}

}
