import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		insert(app);
	}

	@SuppressWarnings("unchecked")
	public void insert(Application app) {
		Map<String, List<Object>> all =
			(Map<String, List<Object>>)Yaml.load("test-data.yml");
			Ebean.save(all.get("members"));
			Ebean.save(all.get("messages"));
			for (Object message : all.get("messages")) {
				Message target = Message.find.byId(((Message) message).id);
				target.member = Member.findByName(target.name);
				target.update();
			}
	}
}