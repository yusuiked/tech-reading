package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Nomikaimember implements Serializable {
	@Id
	private long nomikaimemberid;

	private boolean attend;

	private boolean steward;

	@ManyToOne
	@JoinColumn(name="NOMIKAIID")
	private Nomikai nomikaiid;

	@ManyToOne
	@JoinColumn(name="USERID")
	private User userid;

	private static final long serialVersionUID = 1L;

	public Nomikaimember() {
		super();
	}

	public long getNomikaimemberid() {
		return this.nomikaimemberid;
	}

	public void setNomikaimemberid(long nomikaimemberid) {
		this.nomikaimemberid = nomikaimemberid;
	}

	public boolean isAttend() {
		return this.attend;
	}

	public void setAttend(boolean attend) {
		this.attend = attend;
	}

	public boolean isSteward() {
		return this.steward;
	}

	public void setSteward(boolean steward) {
		this.steward = steward;
	}

	public Nomikai getNomikaiid() {
		return this.nomikaiid;
	}

	public void setNomikaiid(Nomikai nomikaiid) {
		this.nomikaiid = nomikaiid;
	}

	public User getUserid() {
		return this.userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

}
