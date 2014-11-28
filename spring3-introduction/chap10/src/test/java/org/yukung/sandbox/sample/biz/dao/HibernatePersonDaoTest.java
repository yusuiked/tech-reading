package org.yukung.sandbox.sample.biz.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yukung.sandbox.sample.biz.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
public class HibernatePersonDaoTest {

	@Autowired
	private PersonDao dao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindPerson() throws Exception {
		List<Person> people = dao.findPerson();
		assertThat(people, is(notNullValue()));
		assertThat(people.size(), is(3));
		for (Person person : people) {
			assertThat(person.getId(), is(instanceOf(Long.class)));
			assertThat(person.getName(), is(instanceOf(String.class)));
		}
	}

	@Test
	public void testCanAddOne() throws Exception {
		List<Person> beforeAddition = dao.findPerson();
		assertThat(beforeAddition, is(notNullValue()));
		assertThat(beforeAddition.size(), is(3));
		Person newPerson = new Person();
		newPerson.setName("Amuro Ray");
        dao.addPerson(newPerson);
		List<Person> afterAddition = dao.findPerson();
		assertThat(afterAddition, is(notNullValue()));
		assertThat(afterAddition.size(), is(4));
	}

	@Test
	public void testCanUpdateNameSomeone() throws Exception {
		List<Person> people = dao.findPerson();
		Person pilot = people.get(2);
		pilot.setName("Char Aznable");
		dao.updatePerson(pilot);
		List<Person> pilots = dao.findPerson();
		assertThat(pilots.get(2).getName(), is("Char Aznable"));
	}

	@Test
	public void testCanRemoveSomeone() throws Exception {
		List<Person> beforeRemoval = dao.findPerson();
		assertThat(beforeRemoval, is(notNullValue()));
		assertThat(beforeRemoval.size(), is(4));
		dao.removePerson(beforeRemoval.get(1));
		List<Person> afterRemoval = dao.findPerson();
		assertThat(afterRemoval.size(), is(3));
	}
}
