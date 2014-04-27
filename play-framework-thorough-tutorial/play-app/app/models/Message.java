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
	@Pattern(message = "半角英数字のみ受け付けます。", value = "[a-zA-Z]+")
	public String name;
	@Email(message = "妥当なメールアドレスの形式のみ受け付けます。")
	public String mail;
	@Required(message = "必須項目です。")
	@ValidateWith(value = IsUrl.class, message = "URLで始まるメッセージを記述してください。")
	public String message;
	@CreatedTimestamp
	public Date postdate;

	public static Finder<Long, Message> find = new Finder<Long, Message>(Long.class, Message.class);

	@Override
	public String toString() {
		return ("[id:" + id + ", name:" + name + ", mail:" + mail + ", message:" + message + ", date:" + postdate + "]");
	}

	public static class IsUrl extends Validator<String> {
		@Override
		public boolean isValid(String s) {
			return s.toLowerCase().startsWith("http://");
		}

		@Override
		public F.Tuple<String, Object[]> getErrorMessageKey() {
			return new F.Tuple<String, Object[]>("error.invalid", new String[]{});
		}
	}
}