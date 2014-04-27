package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.*;

import com.avaje.ebean.annotation.*;

import play.db.ebean.*;
import play.data.validation.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.ValidateWith;
import play.data.validation.Constraints.Validator;
import play.libs.F;

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

	public static Finder<Long, Message> find = new Finder<Long, Message>(Long.class, Message.class);

	@Override
	public String toString() {
		return ("[id:" + id + ", name:" + name + ", message:" + message + ", date:" + postdate + "]");
	}

	public static Message findByName(String input) {
		return Message.find.where()
			.eq("name", input).findList().get(0);
	}
}