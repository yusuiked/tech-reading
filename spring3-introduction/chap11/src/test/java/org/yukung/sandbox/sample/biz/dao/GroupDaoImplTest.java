package org.yukung.sandbox.sample.biz.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yukung.sandbox.sample.biz.domain.Group;

public class GroupDaoImplTest {

	private GroupDaoImpl groupDao;
	private EntityManager em;

	@Before
	public void setUp() throws Exception {
		groupDao = new GroupDaoImpl();
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("manager1");
		em = emFactory.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testFindGroupByName() {
		List<Group> groups = groupDao.findGroupByName(em, "Zeon");
		assertThat(groups, is(notNullValue()));
		assertThat(groups.size(), is(1));
		for (Group group : groups) {
			assertThat(group.getId(), is(instanceOf(Integer.class)));
			assertThat(group.getName(), is(instanceOf(String.class)));
		}
	}

	@Test
	public void testInsertGroup() throws Exception {
		List<Group> beforeAddition = groupDao.findGroupByName(em, "Titans");
		assertThat(beforeAddition, is(notNullValue()));
		assertThat(beforeAddition.size(), is(0));
		Group group = new Group();
		group.setName("Titans");
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			groupDao.insertGroup(em, group);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		List<Group> afterAddition = groupDao.findGroupByName(em, "Titans");
		assertThat(afterAddition, is(notNullValue()));
		assertThat(afterAddition.size(), is(1));
	}

	@Test
	public void testRemoveGroup() throws Exception {
		List<Group> beforeAddition = groupDao.findGroupByName(em, "Zeon");
		assertThat(beforeAddition, is(notNullValue()));
		assertThat(beforeAddition.size(), is(1));
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			groupDao.removeGroup(em, beforeAddition.get(0));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		List<Group> afterAddition = groupDao.findGroupByName(em, "Titans");
		assertThat(afterAddition, is(notNullValue()));
		assertThat(afterAddition.size(), is(0));
	}
}
