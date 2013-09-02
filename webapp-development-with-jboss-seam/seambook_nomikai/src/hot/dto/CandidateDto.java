package dto;

import java.io.Serializable;

import entity.Nomikaimember;

public class CandidateDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean register;
	private Nomikaimember nomikaimember;

	public boolean isRegister() {
		return register;
	}

	public void setRegister(boolean register) {
		this.register = register;
	}

	public Nomikaimember getNomikaimember() {
		return nomikaimember;
	}

	public void setNomikaimember(Nomikaimember nomikaimember) {
		this.nomikaimember = nomikaimember;
	}

}
