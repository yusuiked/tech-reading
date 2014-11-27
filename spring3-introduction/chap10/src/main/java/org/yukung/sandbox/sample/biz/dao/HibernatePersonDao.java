package org.yukung.sandbox.sample.biz.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.yukung.sandbox.sample.biz.domain.Person;

public class HibernatePersonDao implements PersonDao {

	@Override
	public List<Person> findPerson() {
		return null;
	}

	@Override
	public void addPerson(Person person) {
	}

	@Override
	public void removePerson(Person person) {

	}

	@Override
	public void updatePerson(Person person) {

	}

	public void addPerson(Session session, Person person) {
		try {
			session.save(person);
			session.flush();
		} catch (ConstraintViolationException e) {
			throw new DuplicateKeyException("Primary Key が重複しています。");
		}
	}

	public void updatePerson(Session session, Person person) {
		session.update(person);
		session.flush();
	}

	public void removePerson(Session session, Person person) {
		session.delete(person);
		session.flush();
	}

	public List<Person> findPerson(Session session) {
		return session.createQuery("from Person p order by p.id asc").list();
	}

}
