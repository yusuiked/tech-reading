package org.yukung.sandbox.sample.biz.dao;

import org.yukung.sandbox.sample.biz.domain.Owner;

public interface FriendlyDao {

	Owner getOwnerDynamic(String id, String name);

	void insertOwner(Owner owner);

	void deleteOwner(String id);
}
