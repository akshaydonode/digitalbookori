package com.cts.digitalbook.digitalbookreaderservice.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIPTION_INFO")
public class SubscriptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBSCRIPTION_ID")
	private int subscriptionId;

	@Column(name = "BOOK_ID")
	private int bookId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "READER_ID")
	private ReaderEntity readerEntity;

	@Column(name = "SUBSCRIPTION_DATE")
	private Date subscriptionDate;

	@Column(name = "IS_SUBSCRIBED")
	private boolean subscribed;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public ReaderEntity getReaderId() {
		return readerEntity;
	}

	public void setReaderId(ReaderEntity readerEntity) {
		this.readerEntity = readerEntity;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "SubscriptionEntity [subscriptionId=" + subscriptionId + ", bookId=" + bookId + ", readerEntity="
				+ readerEntity + ", subscriptionDate=" + subscriptionDate + ", subscribed=" + subscribed + ", isActive="
				+ isActive + "]";
	}

}
