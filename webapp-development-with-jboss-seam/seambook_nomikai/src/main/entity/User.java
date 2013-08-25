package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {
	@Id
	private String id;

	private String name;

	private String yakushoku;

	@OneToMany(mappedBy="userid")
	private Set<Nomikaimember> nomikaimemberCollection;

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

	public Set<Nomikaimember> getNomikaimemberCollection() {
		return this.nomikaimemberCollection;
	}

	public void setNomikaimemberCollection(Set<Nomikaimember> nomikaimemberCollection) {
		this.nomikaimemberCollection = nomikaimemberCollection;
	}

}
