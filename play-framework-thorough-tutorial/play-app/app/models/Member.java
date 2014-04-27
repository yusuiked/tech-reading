package models;

import javax.persistence.*;

import com.avaje.ebean.annotation.*;

import play.data.validation.Constraints.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import java.util.*;

@Entity
public class Member extends Model {

	@Id
	public Long id;

	@Required(message = "必須項目です。")
	public String name;

	@Email(message = "メールアドレスを記入してください。")
	public String mail;

	public String tel;

	public static Finder<Long, Member> find = new Finder<Long, Member>(Long.class, Member.class);

	@Override
	public String toString() {
		return ("[id:" + id + ", name:" + name + ", mail:" + mail + ", tel:" + tel + "]");
	}

	public static Member findByName(String input) {
		return Member.find.where()
			.eq("name", input).findList().get(0);
	}
}