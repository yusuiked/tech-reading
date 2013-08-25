package org.domain.seambook_userkanri.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.domain.seambook_userkanri.entity.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

@Scope(ScopeType.CONVERSATION)
@Name("userListAction")
public class UserListAction {

	@DataModel("users")
	List<User> users;

	@DataModelSelection("users")
	User selectedUser;

	@Out(required = false)
	User user;

	@In
	EntityManager entityManager;

	public String searchUser() {
		Query query = entityManager.createQuery("select u from User u");
		users = query.getResultList();
		return "success";
	}

	public String showDetail() {
		user = selectedUser;
		return "detail";
	}
}
