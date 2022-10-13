package com.cts.digitalbook.digitalbookbookservice.dtos;

public class BookEntityDTO {
	

	private String title;
	private String category;
	private String price;
	private String publisher;
	private String contents;
	private String active;
	private String authorId;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	@Override
	public String toString() {
		return "BookEntityDTO [title=" + title + ", category=" + category + ", price=" + price + ", publisher="
				+ publisher + ", contents=" + contents + ", active=" + active + ", authorId=" + authorId + "]";
	}



}
