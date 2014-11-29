package org.yukung.sandbox.sample.biz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.yukung.sandbox.sample.biz.domain.Group;

public class GroupDaoImpl implements GroupDao {

	@Override
	public List<Group> findGroupByName(String name) {
		return null;
	}

	@Override
	public void insertGroup(Group group) {

	}

	@Override
	public void removeGroup(Group group) {

	}

	public List<Group> findGroupByName(EntityManager em, String name) {
		Query query = em.createQuery("select g from Group g where g.name like ?1");
		query.setParameter(1,  "%"+name+"%");
		return query.getResultList();
	}

	public void insertGroup(EntityManager em, Group group) {
		em.persist(group);
		em.flush();
	}

	public void removeGroup(EntityManager em, Group group) {
		em.remove(group);
		em.flush();
	}

	private EntityManager createEntityManager() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("manager1");
		return emfactory.createEntityManager();
	}
}
