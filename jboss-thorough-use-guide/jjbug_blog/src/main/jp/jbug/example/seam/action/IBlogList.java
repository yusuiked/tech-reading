package jp.jbug.example.seam.action;

import javax.ejb.Local;

import jp.jbug.example.seam.model.BlogEntry;

@Local
public interface IBlogList {

	void getBlogEntries();

	void deleteBlogEntry();

	void deleteComment(BlogEntry entry);

	void destroy();
}
