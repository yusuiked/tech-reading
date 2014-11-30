package org.yukung.sandbox.sample.biz.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yukung.sandbox.sample.biz.domain.Owner;
import org.yukung.sandbox.sample.biz.domain.Pet;

public class PetDaoImplTest {

	private PetDaoImpl petDao;
	private SqlSessionFactory sessionFactory;

	@Before
	public void setUp() throws Exception {
		petDao = new PetDaoImpl();
		sessionFactory = createSqlSessionFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindPetById() {
		SqlSession session = sessionFactory.openSession();
		Pet pet = petDao.findPetById(session, 1);
		assertThat(pet, is(notNullValue()));
		assertThat(pet, isA(Pet.class));
		assertThat(pet.getPetId(), is(1));
		assertThat(pet.getPetName(), is("aaa"));
		assertThat(pet.getOwnerName(), is("toki"));
		assertThat(pet.getPrice(), is(1000));
		assertThat(pet.getBirthDate(), is(nullValue()));
		session.close();
	}

	@Test
	public void testInsertPet() throws Exception {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			Pet beforeAddition = petDao.findPetById(session, 2);
			assertThat(beforeAddition, is(nullValue()));
			Pet newPet = new Pet();
			newPet.setPetName("たま");
			newPet.setOwnerName("東京太郎");
			newPet.setPrice(3000);
			newPet.setBirthDate(new Date());
			session.insert(
					"org.yukung.sandbox.sample.biz.dao.PetMapper.insertPet",
					newPet);
			assertThat(newPet.getPetId(), is(2));
			Pet afterAddition = petDao.findPetById(session, newPet.getPetId());
			assertThat(afterAddition, is(notNullValue()));
			assertThat(afterAddition.getPetName(), is("たま"));
			assertThat(afterAddition.getOwnerName(), is("東京太郎"));
			assertThat(afterAddition.getPrice(), is(3000));
			assertThat(afterAddition.getBirthDate(), is(notNullValue()));
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Test
	public void testSelectPetByNames() throws Exception {
		SqlSession session = sessionFactory.openSession();
		Map<String, Object> map = new HashMap<>();
		Pet pet = new Pet();
		pet.setPetName("aaa");
		map.put("pet", pet);
		Owner owner = new Owner();
		owner.setOwnerName("toki");
		map.put("owner", owner);
		Pet result = session.selectOne("org.yukung.sandbox.sample.biz.dao.PetMapper.selectPetByNames", map);
		assertThat(result, is(notNullValue()));
		assertThat(result.getPetName(), is("aaa"));
		assertThat(result.getOwnerName(), is("toki"));
		session.close();
	}

	@Test
	public void testSelectOwner() throws Exception {
		SqlSession session = sessionFactory.openSession();
		Owner owner = session.selectOne("org.yukung.sandbox.sample.biz.dao.PetMapper.selectOwner", "toki");
		assertThat(owner, is(notNullValue()));
		assertThat(owner.getOwnerName(), is("toki"));
		assertThat(owner.getPets(), is(notNullValue()));
		assertThat(owner.getPets().size(), is(1));
		List<Pet> pets = owner.getPets();
		for (Pet pet : pets) {
			assertThat(pet.getPetName(), is("aaa"));
		}
		session.close();
	}

	@Test
	public void testfindPet() throws Exception {
		SqlSession session = sessionFactory.openSession();
		Pet pet = new Pet();
		pet.setPrice(1000);
		List<Pet> pets = session.selectList("org.yukung.sandbox.sample.biz.dao.PetMapper.findPet", pet);
		assertThat(pets, is(notNullValue()));
		assertThat(pets.size(), is(1));
		for (Pet result : pets) {
			assertThat(result.getPetName(), is("aaa"));
		}
		session.close();
	}

	@Test
	public void testSelectPetMapper() throws Exception {
		SqlSession session = sessionFactory.openSession();
		PetMapper mapper = session.getMapper(PetMapper.class);
		Pet pet = mapper.selectPet(1);
		assertThat(pet, is(notNullValue()));
		assertThat(pet.getPetName(), is("aaa"));
		session.close();
	}

	@Test
	public void testDeletePet() throws Exception {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			Pet beforeDeletion = session.selectOne("org.yukung.sandbox.sample.biz.dao.PetMapper.selectPet", 1);
			assertThat(beforeDeletion, is(notNullValue()));
			session.delete("org.yukung.sandbox.sample.biz.dao.PetMapper.deletePet", "aaa");
			Pet afterDeletion = session.selectOne("org.yukung.sandbox.sample.biz.dao.PetMapper.selectPet", 1);
			assertThat(afterDeletion, is(nullValue()));
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private SqlSessionFactory createSqlSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		try {
			Reader reader = Resources.getResourceAsReader("mybatis.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}
}
