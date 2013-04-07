package org.yukung.practice.chap7;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

public class SerializeTest {

	private static final String DAT_FILE_PATH = "src/test/resources/company.dat";

	@Test
	public void testSerialize() {
		Employee employee = new Employee("田中太郎", 41);
		Department department = new Department("総務部", employee);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(
					DAT_FILE_PATH));
			oos.writeObject(department);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testDeserialize() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DAT_FILE_PATH));
		Object object = ois.readObject();
		assertThat(object, is(instanceOf(Department.class)));
		Department department = (Department) object;
		assertThat(department.getName(), is("総務部"));
		assertThat(department.getEmployee(), is(notNullValue()));
		assertThat(department.getEmployee().getName(), is("田中太郎"));
		assertThat(department.getEmployee().getAge(), is(41));
	}
}
