package org.yukung.sandbox.sample.biz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.yukung.sandbox.sample.biz.domain.Group;

@Repository
public class GroupDaoImpl implements GroupDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Group> findGroupByName(String name) {
		Query query = em.createQuery("select g from Group g where g.name like ?1");
		query.setParameter(1, "%"+name+"%");
		return query.getResultList();
	}

	@Override
	public void insertGroup(Group group) {
		em.persist(group);
		em.flush();
	}

	@Override
	public void removeGroup(Group group) {
		em.remove(group);
		em.flush();
	}
}
