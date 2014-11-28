package org.yukung.sandbox.sample.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yukung.sandbox.sample.biz.dao.PersonDao;
import org.yukung.sandbox.sample.biz.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public List<Person> findPerson() {
		return personDao.findPerson();
	}

	@Override
	public void addPerson(Person person) {
		personDao.addPerson(person);
	}

	@Override
	public void updatePerson(Person person) {
		personDao.updatePerson(person);
	}

	@Override
	public void removePerson(Person person) {
		personDao.removePerson(person);
	}
}
