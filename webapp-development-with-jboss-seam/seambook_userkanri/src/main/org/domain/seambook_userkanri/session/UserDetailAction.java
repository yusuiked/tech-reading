package org.domain.seambook_userkanri.session;

import javax.persistence.EntityManager;

import org.domain.seambook_userkanri.entity.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Scope(ScopeType.CONVERSATION)
@Name("userDetailAction")
public class UserDetailAction {

	@In
	User user;

	@In
	EntityManager entityManager;

	public String updateUser() {
		return "update_success";
	}

	public String deleteUser() {
		entityManager.remove(user);
		return "delete_success";
	}
}
