package org.yukung.sandbox.sample.biz.dao;

import java.util.List;

import org.yukung.sandbox.sample.biz.domain.Group;

public interface GroupDao {

	List<Group> findGroupByName(String name);

	void insertGroup(Group group);

	void removeGroup(Group group);
}
