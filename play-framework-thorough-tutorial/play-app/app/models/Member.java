package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.annotation.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.validation.Constraints.*;

@Entity
public class Member extends Model {
	
	@Id
	public Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	public List<Message> messages = new ArrayList<Message>();
	
	@Required(message = "必須項目です。")
	public String name;

	@Email(message = "メールアドレスを記入ください。")
	public String mail;

	public String tel;

	public static Finder<Long, Member> find = 
		new Finder<Long, Member>(Long.class, Member.class);
		
	@Override
	public String toString(){
		String ids = "{id:";
		for (Message m : messages) {
			ids += " " + m.id;
		}
		ids += "}";
		return ("[id:" + id + ", message:" + ids +
			", name:" + name + ", mail:" + 
			mail + ", tel:" + tel + "]");
	}

	public static Member findByName(String input) {
		return Member.find.where()
				.eq("name", input ).findList().get(0);
	}
	
}
