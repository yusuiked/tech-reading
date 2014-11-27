package org.yukung.sandbox.sample.biz.service;

import java.util.List;

import org.yukung.sandbox.sample.biz.domain.Person;

public interface PersonService {

	List<Person> findPerson();

	void addPerson(Person person);

	void updatePerson(Person person);

	void removePerson(Person person);
}
