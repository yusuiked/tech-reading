package org.yukung.sandbox.sample.biz.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.yukung.sandbox.sample.biz.domain.Owner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
@Transactional
public class FriendlyDaoImplTest {

	@Autowired
	private FriendlyDao friendlyDao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetOwnerDynamic() {
		Owner owner = friendlyDao.getOwnerDynamic("1", "toki");
		assertThat(owner, is(notNullValue()));
		assertThat(owner.getOwnerId(), is("1"));
		assertThat(owner.getOwnerName(), is("toki"));
	}

	@Test
	public void testInsertOwner() {
		Owner beforeAddition = friendlyDao.getOwnerDynamic(null, "newOwner");
		assertThat(beforeAddition, is(nullValue()));
		Owner newOwner = new Owner();
		newOwner.setOwnerName("newOwner");
		friendlyDao.insertOwner(newOwner);
		assertThat(newOwner.getOwnerId(), is(notNullValue()));
		Owner afterAddition = friendlyDao.getOwnerDynamic(newOwner.getOwnerId(), "newOwner");
		assertThat(afterAddition, is(notNullValue()));
		assertThat(afterAddition.getOwnerId(), is(not("0")));
		assertThat(afterAddition.getOwnerName(), is("newOwner"));
	}

	@Test
	public void testDeleteOwner() {
		Owner beforeAddition = friendlyDao.getOwnerDynamic("1", "toki");
		assertThat(beforeAddition, is(notNullValue()));
		friendlyDao.deleteOwner(beforeAddition.getOwnerId());
		Owner afterAddition = friendlyDao.getOwnerDynamic(null, "toki");
		assertThat(afterAddition, is(nullValue()));
	}
}
