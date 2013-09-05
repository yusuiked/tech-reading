package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries(@NamedQuery(name = "findById", query = "select t from Nomikai t where t.nomikaiid = :nomikaiid"))
@Entity
public class Nomikai implements Serializable {
	@Id
	@GeneratedValue
	private long nomikaiid;

	private Date date;

	private String detail;

	private String name;

	private String lat = "35.466253842292964";

	private String lon = "139.622980000035555555555621643";

	private String zoom = "17";

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nomikaiid")
	private Set<Nomikaimember> nomikaimemberCollection;

	private static final long serialVersionUID = 1L;

	public Nomikai() {
		super();
	}

	public long getNomikaiid() {
		return this.nomikaiid;
	}

	public void setNomikaiid(long nomikaiid) {
		this.nomikaiid = nomikaiid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public Set<Nomikaimember> getNomikaimemberCollection() {
		return this.nomikaimemberCollection;
	}

	public void setNomikaimemberCollection(
			Set<Nomikaimember> nomikaimemberCollection) {
		this.nomikaimemberCollection = nomikaimemberCollection;
	}

}
