package jp.jbug.example.seam.action;

import javax.ejb.Local;

import jp.jbug.example.seam.model.BlogEntry;

@Local
public interface IBlogEntry {

	String init();

	String init(BlogEntry entry);

	String save();

	String update();

	void destroy();
}
