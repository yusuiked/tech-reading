package jp.jbug.example.seam.action;

import javax.ejb.Local;

import jp.jbug.example.seam.model.BlogEntry;

@Local
public interface IBlogEntry {

	void init();

	void init(BlogEntry entry);

	void confirm();

	void save();

	void update();

	void destroy();
}
