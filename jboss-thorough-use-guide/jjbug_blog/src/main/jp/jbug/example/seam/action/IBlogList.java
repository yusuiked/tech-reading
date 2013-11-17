package jp.jbug.example.seam.action;

import javax.ejb.Local;

@Local
public interface IBlogList {

	String getBlogEntries();

	String deleteBlogEntry();

	void destroy();
}
