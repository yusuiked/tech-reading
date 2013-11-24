package jp.jbug.example.seam.action;

import javax.ejb.Stateless;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

@Stateless
@Name("authenticator")
public class AuthenticatorBean implements Authenticator {
	@In
	Identity identity;
	@In
	Credentials credentials;

	public boolean authenticate() {
		if (identity.getUsername().equals("kacho")) {
			identity.addRole("manager");
		} else if (identity.getUsername().equals("admin")) {
			identity.addRole("admin");
		} else {
			return false;
		}
		return true;
	}

}
