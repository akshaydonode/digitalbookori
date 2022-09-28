package com.cts.digitalbook.digitalbookreaderservice.entities;

import java.util.List;

public class SubscribeDetailsEntity {

	public int readerId;
	public List<Integer> bookIds;

	public int getReaderId() {
		return readerId;
	}

	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}

	public List<Integer> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<Integer> bookIds) {
		this.bookIds = bookIds;
	}

}
