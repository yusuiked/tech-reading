package org.yukung.sandbox.sample.biz.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yukung.sandbox.sample.biz.domain.Person;

public class HibernatePersonDaoTest {

	private HibernatePersonDao dao;
	private Session session;

	@Before
	public void setUp() throws Exception {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		session = sessionFactory.openSession();
		dao = new HibernatePersonDao();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

	@Test
	public void testFindPerson() throws Exception {
		List<Person> people = dao.findPerson(session);
		assertThat(people, is(notNullValue()));
		assertThat(people.size(), is(3));
		for (Person person : people) {
			assertThat(person.getId(), is(instanceOf(Long.class)));
			assertThat(person.getName(), is(instanceOf(String.class)));
		}
	}

	@Test
	public void testCanAddOne() throws Exception {
		List<Person> beforeAddition = dao.findPerson(session);
		assertThat(beforeAddition, is(notNullValue()));
		assertThat(beforeAddition.size(), is(3));
		Person newPerson = new Person();
		newPerson.setName("Amuro Ray");
		Transaction tx = session.beginTransaction();
		try {
			dao.addPerson(session, newPerson);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		}
		List<Person> afterAddition = dao.findPerson(session);
		assertThat(afterAddition, is(notNullValue()));
		assertThat(afterAddition.size(), is(4));
	}

	@Test
	public void testCanUpdateNameSomeone() throws Exception {
		List<Person> people = dao.findPerson(session);
		Person pilot = people.get(2);
		pilot.setName("Char Aznable");
		Transaction tx = session.beginTransaction();
		try {
			dao.updatePerson(session, pilot);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		}
		List<Person> pilots = dao.findPerson(session);
		assertThat(pilots.get(2).getName(), is("Char Aznable"));
	}

	@Test
	public void testCanRemoveSomeone() throws Exception {
		List<Person> beforeRemoval = dao.findPerson(session);
		assertThat(beforeRemoval, is(notNullValue()));
		assertThat(beforeRemoval.size(), is(3));
		Transaction tx = session.beginTransaction();
		try {
			dao.removePerson(session, beforeRemoval.get(1));
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		}
		List<Person> afterRemoval = dao.findPerson(session);
		assertThat(afterRemoval.size(), is(2));
	}
}
