package dto;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@AutoCreate
@Scope(ScopeType.CONVERSATION)
@Name("nomikaiDto")
public class NomikaiDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long nomikaiid;

	public Long getNomikaiid() {
		return nomikaiid;
	}

	public void setNomikaiid(Long nomikaiid) {
		this.nomikaiid = nomikaiid;
	}
}
