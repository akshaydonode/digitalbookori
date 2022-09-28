package com.cts.digitalbook.digitalbookreaderservice.dtos;

public class BookSubscribeDTO {

	public int bookId;
	public int readerId;
	public String readerEmail;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getReaderId() {
		return readerId;
	}

	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}

	public String getReaderEmail() {
		return readerEmail;
	}

	public void setReaderEmail(String readerEmail) {
		this.readerEmail = readerEmail;
	}

}
