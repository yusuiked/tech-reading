package org.domain.seambook_userkanri.session;

import javax.persistence.EntityManager;

import org.domain.seambook_userkanri.entity.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Scope(ScopeType.CONVERSATION)
@Name("userCreateAction")
public class UserCreateAction {

	@In
	User user;

	@In
	EntityManager entityManager;

	public String createUser() {
		entityManager.persist(user);
		return "create_success";
	}
}
