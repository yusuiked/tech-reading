package org.yukung.sandbox.sample.biz.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.yukung.sandbox.sample.biz.dao.HibernatePersonDao;
import org.yukung.sandbox.sample.biz.domain.Person;

public class PersonServiceImpl implements PersonService {

	@Override
	public List<Person> findPerson() {
		Session session = getSession();
		return new HibernatePersonDao().findPerson(session);
	}

	@Override
	public void addPerson(Person person) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			new HibernatePersonDao().addPerson(session, person);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException ex) {
					System.err.println("システムエラー。");
				}
			}
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					System.err.println("システムエラー。");
				}
			}
		}
	}

	@Override
	public void updatePerson(Person person) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			new HibernatePersonDao().updatePerson(session, person);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException ex) {
					System.err.println("システムエラー。");
				}
			}
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					System.err.println("システムエラー。");
				}
			}
		}
	}

	@Override
	public void removePerson(Person person) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			new HibernatePersonDao().removePerson(session, person);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException ex) {
					System.err.println("システムエラー。");
				}
			}
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					System.err.println("システムエラー。");
				}
			}
		}
	}

	private Session getSession() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		return sessionFactory.openSession();
	}

}
