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
import org.springframework.transaction.annotation.Transactional;
import org.yukung.sandbox.sample.biz.domain.Group;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
@Transactional
public class GroupDaoImplTest {

	@Autowired
	private GroupDao groupDao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindGroupByName() {
		List<Group> groups = groupDao.findGroupByName("Zeon");
		assertThat(groups, is(notNullValue()));
		assertThat(groups.size(), is(1));
		for (Group group : groups) {
			assertThat(group.getId(), is(instanceOf(Integer.class)));
			assertThat(group.getName(), is(instanceOf(String.class)));
		}
	}

	@Test
	public void testInsertGroup() throws Exception {
		List<Group> beforeAddition = groupDao.findGroupByName("Titans");
		assertThat(beforeAddition, is(notNullValue()));
		assertThat(beforeAddition.size(), is(0));
		Group group = new Group();
		group.setName("Titans");
		groupDao.insertGroup(group);
		List<Group> afterAddition = groupDao.findGroupByName("Titans");
		assertThat(afterAddition, is(notNullValue()));
		assertThat(afterAddition.size(), is(1));
	}

	@Test
	public void testRemoveGroup() throws Exception {
		List<Group> beforeAddition = groupDao.findGroupByName("Zeon");
		assertThat(beforeAddition, is(notNullValue()));
		assertThat(beforeAddition.size(), is(1));
		groupDao.removeGroup(beforeAddition.get(0));
		List<Group> afterAddition = groupDao.findGroupByName("Titans");
		assertThat(afterAddition, is(notNullValue()));
		assertThat(afterAddition.size(), is(0));
	}
}
