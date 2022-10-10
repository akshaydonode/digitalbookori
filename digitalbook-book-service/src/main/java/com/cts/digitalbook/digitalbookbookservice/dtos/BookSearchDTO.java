package com.cts.digitalbook.digitalbookbookservice.dtos;

public class BookSearchDTO {

	private double price;
	private String authorName;
	private String category;
	private String publisher;
	private String title;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "BookSearchDTO [price=" + price + ", authorName=" + authorName + ", category=" + category
				+ ", publisher=" + publisher + ", title=" + title + "]";
	}

}
