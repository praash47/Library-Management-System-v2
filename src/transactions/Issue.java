package transactions;

import librarian.*;

import java.io.Serializable;

import generaluser.*;


public class Issue implements Serializable {
	public enum IssueStatus {
		PENDINGACCEPT,
		ACCEPTED,
		REJECTED,
		PENDINGRETURN,
		REJECTEDRETURN,
		RETURNED,
		CANCELED
	}
	
	public Librarian issuedBy;
	public Book book;
	public GeneralUser issuedTo;
	public IssueStatus status = IssueStatus.PENDINGACCEPT;
	
	public Issue() {}
	
	public void requestIssue(GeneralUser issuedTo, Book book) {
		this.issuedTo = issuedTo;
		this.book = book;
	}
	public String getStatusName() {
		switch(status) {
		case PENDINGACCEPT:
			return "Issue Pending";
		case ACCEPTED:
			return "Issue Accepted";
		case REJECTED:
			return "Issue Rejected";
		case PENDINGRETURN:
			return "Return Pending";
		case REJECTEDRETURN:
			return "Return Rejected";
		case RETURNED:
			return "Returned";
		case CANCELED:
			return "Canceled";
		default:
			return "Unknown";
		}
	}
}
