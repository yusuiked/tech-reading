package jp.jbug.example.seam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.jboss.seam.annotations.Name;

@Entity
@Name("blogEntry")
public class BlogEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long blogEntryId;
	@Column(name = "BLOGDATE", nullable = false)
	private Date blogDate;
	@Column(nullable = false, length = 20)
	private String category;
	@Column(nullable = false)
	private String blogEntry;

	public Date getBlogDate() {
		return blogDate;
	}

	public String getBlogEntry() {
		return blogEntry;
	}

	public Long getBlogEntryId() {
		return blogEntryId;
	}

	public String getCategory() {
		return category;
	}

	public void setBlogDate(Date blogDate) {
		this.blogDate = blogDate;
	}

	public void setBlogEntry(String blogEntry) {
		this.blogEntry = blogEntry;
	}

	public void setBlogEntryId(Long blogEntryId) {
		this.blogEntryId = blogEntryId;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
