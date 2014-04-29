package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.annotation.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

@Entity
public class Message extends Model {
	
	@Id
	public Long id;
		 
	@ManyToOne(cascade=CascadeType.ALL)
	public Member member;

	@Required(message = "必須項目です。")
	public String name;
	
	@Required(message = "必須項目です。")
	public String message;
	
	@CreatedTimestamp
	public Date postdate;
	
	public static Finder<Long, Message> find = 
		new Finder<Long, Message>(Long.class, Message.class);
	
	@Override
	public String toString(){
		String mems = "{" + this.name + "}";
		return ("[id:" + id + 
			", message:" + message + ", members:" + mems
			+ ", date:" + postdate + "]");
	}

	public static Message findByName(String input) {
		return Message.find.where()
				.eq("name", input ).findList().get(0);
	}

}
