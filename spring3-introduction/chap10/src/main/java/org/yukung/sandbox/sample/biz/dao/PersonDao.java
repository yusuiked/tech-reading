package org.yukung.sandbox.sample.biz.dao;

import java.util.List;

import org.yukung.sandbox.sample.biz.domain.Person;

public interface PersonDao {
	List<Person> findPerson();

	void addPerson(Person person);

	void removePerson(Person person);

	void updatePerson(Person person);
}
