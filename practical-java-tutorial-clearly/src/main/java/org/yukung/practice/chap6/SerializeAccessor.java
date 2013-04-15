package org.yukung.practice.chap6;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeAccessor {

	public void serialize() {
		Person person = new Person("hoge", 32, "Japan");
		try (ObjectOutputStream serializer = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("person.dat")))) {
			serializer.writeObject(person);
			serializer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deserialize() {
		Person person = null;
		try (ObjectInputStream deserializer = new ObjectInputStream(new BufferedInputStream(new FileInputStream("person.dat")))) {
			Object object = deserializer.readObject();
			if (object instanceof Person) {
				person = Person.class.cast(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(person);
	}

	public static void main(String[] args) {
		SerializeAccessor accessor = new SerializeAccessor();
		accessor.serialize();
		accessor.deserialize();
	}
}
