package dto;

import java.io.Serializable;

import org.jboss.seam.annotations.Name;

@Name("nomikaiDetailDto")
public class NomikaiDetailDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long nomikaiid;

	public Long getNomikaiid() {
		return nomikaiid;
	}

	public void setNomikaiid(Long nomikaiid) {
		this.nomikaiid = nomikaiid;
	}

}
