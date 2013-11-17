package jp.jbug.example.seam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jboss.seam.annotations.Name;

@Entity
@Name("comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long commentId;

	@Column(nullable = false, length = 20)
	private String commentater;

	@Column(nullable = false, length = 100)
	private String comment;

	@ManyToOne
	@JoinColumn(name = "blogEntryId", referencedColumnName = "blogEntryId")
	private BlogEntry blogEntry;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getCommentater() {
		return commentater;
	}

	public void setCommentater(String commentater) {
		this.commentater = commentater;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BlogEntry getBlogEntry() {
		return blogEntry;
	}

	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}
}
