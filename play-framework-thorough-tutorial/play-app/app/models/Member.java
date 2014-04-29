package models;

import com.avaje.ebean.annotation.*;

import play.data.validation.Constraints.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import java.util.*;
import javax.persistence.*;

@Entity
public class Member extends Model {

	@Id
	public Long id;

	@Required(message = "必須項目です。")
	public String name;

	@Email(message = "メールアドレスを記入してください。")
	public String mail;

	public String tel;

	@ManyToMany
	public List<Message> messages = new ArrayList<Message>();

	public static Finder<Long, Member> find = new Finder<Long, Member>(Long.class, Member.class);

	@Override
	public String toString() {
		String ids = "{id:";
		for (Message m : messages) {
			ids += " " + m.id;
		}
		ids += "}";
		return "[id:" + id + ", message:" + ids +
			", name:" + name + ", mail:" + mail +
			", tel:" + tel + "]";
	}

	public static Member findByName(String input) {
		return Member.find.where()
			.eq("name", input).findList().get(0);
	}
}