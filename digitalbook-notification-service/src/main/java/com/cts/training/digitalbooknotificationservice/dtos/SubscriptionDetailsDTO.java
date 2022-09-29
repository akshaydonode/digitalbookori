package com.cts.training.digitalbooknotificationservice.dtos;

import java.util.List;

public class SubscriptionDetailsDTO {

	public int bookId;
	public List<Integer> readerId;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public List<Integer> getReaderId() {
		return readerId;
	}

	public void setReaderId(List<Integer> readerId) {
		this.readerId = readerId;
	}

	@Override
	public String toString() {
		return "SubscriptionDetailsDTO [bookId=" + bookId + ", readerId=" + readerId + "]";
	}

}
