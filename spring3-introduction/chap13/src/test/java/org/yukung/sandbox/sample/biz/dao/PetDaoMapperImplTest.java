package org.yukung.sandbox.sample.biz.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.yukung.sandbox.sample.biz.domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/context.xml")
@Transactional
public class PetDaoMapperImplTest {

	@Autowired
	private PetDao petDao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectPetMapper() throws Exception {
		Pet pet = petDao.findPetById(1);
		assertThat(pet, is(notNullValue()));
		assertThat(pet.getPetName(), is("aaa"));
	}

}
