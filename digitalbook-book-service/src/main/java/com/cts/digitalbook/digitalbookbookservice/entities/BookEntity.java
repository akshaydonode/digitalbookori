package com.cts.digitalbook.digitalbookbookservice.entities;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_DETAILS")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_ID")
	private int bookId;

	@Column(name = "BOOK_TITLE")
	private String title;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "PRICE")
	private double price;

	@Column(name = "PUBLISHER")
	private String publisher;

	@Column(name = "PUBLISHED")
	private Date published;

	@Column(name = "CONTENTS")
	private String contents;

	@Column(name = "IS_ACTIVE")
	private boolean isAtive;

//	@Lob
//	@Column(name = "BOOK_LOGO")
//	private byte[] logo;

	@Column(name = "AUTHOR_ID")
	private int authorId;

	@Column(name = "UPDATED_DATE")
	private Date updateDate;

//	@Column(name = "IMAGE_TYPE")
//	private String imageType;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String chapters) {
		contents = chapters;
	}

	

//	public byte[] getLogo() {
//		return logo;
//	}
//
//	public void setLogo(byte[] logo) {
//		this.logo = logo;
//	}

	public boolean getAtive() {
		return isAtive;
	}

	public void setAtive(boolean isAtive) {
		this.isAtive = isAtive;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "BookEntity [bookId=" + bookId + ", title=" + title + ", category=" + category + ", price=" + price
				+ ", publisher=" + publisher + ", published=" + published + ", contents=" + contents + ", isAtive="
				+ isAtive + ", authorId=" + authorId + ", updateDate=" + updateDate + "]";
	}


//	public String getImageType() {
//		return imageType;
//	}
//
//	public void setImageType(String imageType) {
//		this.imageType = imageType;
//	}

	

}
