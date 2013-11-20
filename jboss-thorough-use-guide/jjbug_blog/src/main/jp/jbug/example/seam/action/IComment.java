package jp.jbug.example.seam.action;

import javax.ejb.Local;

import jp.jbug.example.seam.model.BlogEntry;

@Local
public interface IComment {

	void init(BlogEntry entry);

	void save();

	void destroy();
}
