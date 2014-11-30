package org.yukung.sandbox.sample.biz.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yukung.sandbox.sample.biz.domain.Owner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class FriendlyDaoImplTest {

	private FriendlyDaoImpl friendlyDao;
	private SqlMapClient sqlMapClient;

	@Before
	public void setUp() throws Exception {
		friendlyDao = new FriendlyDaoImpl();
		sqlMapClient = createSqlMapClient();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetOwnerDynamic() {
		Owner owner = friendlyDao.getOwnerDynamic(sqlMapClient, "1", "toki");
		assertThat(owner, is(notNullValue()));
		assertThat(owner.getOwnerId(), is("1"));
		assertThat(owner.getOwnerName(), is("toki"));
	}

	@Test
	public void testInsertOwner() {
		try {
			sqlMapClient.startTransaction();
			Owner beforeAddition = friendlyDao.getOwnerDynamic(sqlMapClient, null, "newOwner");
			assertThat(beforeAddition, is(nullValue()));
			Owner newOwner = new Owner();
			newOwner.setOwnerName("newOwner");
			friendlyDao.insertOwner(sqlMapClient, newOwner);
			assertThat(newOwner.getOwnerId(), is(notNullValue()));
			Owner afterAddition = friendlyDao.getOwnerDynamic(sqlMapClient, newOwner.getOwnerId(), "newOwner");
			assertThat(afterAddition, is(notNullValue()));
			assertThat(afterAddition.getOwnerId(), is(not("0")));
			assertThat(afterAddition.getOwnerName(), is("newOwner"));
			sqlMapClient.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testDeleteOwner() {
		try {
			sqlMapClient.startTransaction();
			Owner beforeAddition = friendlyDao.getOwnerDynamic(sqlMapClient, "1", "toki");
			assertThat(beforeAddition, is(notNullValue()));
			friendlyDao.deleteOwner(sqlMapClient, beforeAddition.getOwnerId());
			Owner afterAddition = friendlyDao.getOwnerDynamic(sqlMapClient, null, "toki");
			assertThat(afterAddition, is(nullValue()));
			sqlMapClient.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private SqlMapClient createSqlMapClient() {
		SqlMapClient sqlMapClient = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("sqlMapConfig.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlMapClient;
	}

}
