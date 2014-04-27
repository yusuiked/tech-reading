package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.*;

import com.avaje.ebean.annotation.*;

import play.db.ebean.*;
import play.data.validation.*;
import play.data.validation.Constraints.*;

@Entity
public class Message extends Model {
	@Id
	public Long id;
	@Required(message = "必須項目です。")
	@Pattern(message = "半角英数字のみ受け付けます。", value = "[a-zA-Z]+")
	public String name;
	@Email(message = "妥当なメールアドレスの形式のみ受け付けます。")
	public String mail;
	@Required
	@MinLength(message = "最低10文字以上で入力してください。", value = 10)
	@MaxLength(message = "最大200文字以下で入力してください。", value = 200)
	public String message;
	@CreatedTimestamp
	public Date postdate;

	public static Finder<Long, Message> find = new Finder<Long, Message>(Long.class, Message.class);

	@Override
	public String toString() {
		return ("[id:" + id + ", name:" + name + ", mail:" + mail + ", message:" + message + ", date:" + postdate + "]");
	}
}