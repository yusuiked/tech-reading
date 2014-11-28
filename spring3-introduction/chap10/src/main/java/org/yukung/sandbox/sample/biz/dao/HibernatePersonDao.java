package org.yukung.sandbox.sample.biz.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
//import org.hibernate.Session;
//import org.hibernate.exception.ConstraintViolationException;
//import org.springframework.dao.DuplicateKeyException;
import org.yukung.sandbox.sample.biz.domain.Person;

@Repository
public class HibernatePersonDao implements PersonDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Person> findPerson() {
		return hibernateTemplate.find("from Person p order by p.id asc");
	}

	@Override
	public void addPerson(Person person) {
		hibernateTemplate.save(person);
	}

	@Override
	public void removePerson(Person person) {
		hibernateTemplate.delete(person);
	}

	@Override
	public void updatePerson(Person person) {
		hibernateTemplate.update(person);
	}

//	Session オブジェクトの管理は Spring が行うため引数での受け渡しは不要
//	public void addPerson(Session session, Person person) {
//		try {
//			session.save(person);
//			session.flush();
//		} catch (ConstraintViolationException e) {
//			throw new DuplicateKeyException("Primary Key が重複しています。");
//		}
//	}
//
//	public void updatePerson(Session session, Person person) {
//		session.update(person);
//		session.flush();
//	}
//
//	public void removePerson(Session session, Person person) {
//		session.delete(person);
//		session.flush();
//	}
//
//	public List<Person> findPerson(Session session) {
//		return session.createQuery("from Person p order by p.id asc").list();
//	}

}
