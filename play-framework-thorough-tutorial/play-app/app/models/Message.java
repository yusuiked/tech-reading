package models;

import com.avaje.ebean.annotation.*;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

import java.util.*;
import javax.persistence.*;

@Entity
public class Message extends Model {
	@Id
	public Long id;

	@Required(message = "必須項目です。")
	public String name;

	@Required(message = "必須項目です。")
	public String message;

	@CreatedTimestamp
	public Date postdate;

	@ManyToOne(cascade = CascadeType.ALL)
	public Member member;

	public static Finder<Long, Message> find = new Finder<Long, Message>(Long.class, Message.class);

	@Override
	public String toString() {
		return "[id:" + id + ", message:" + message +
			", member:" + member +
			", date:" + postdate + "]";
	}

	public static Message findByName(String input) {
		return Message.find.where()
			.eq("name", input).findList().get(0);
	}
}