package jp.jbug.example.seam.action;

import javax.ejb.Local;

import jp.jbug.example.seam.model.BlogEntry;

@Local
public interface IBlogList {

	String getBlogEntries();

	String deleteBlogEntry();

	String deleteComment(BlogEntry entry);

	void destroy();
}
