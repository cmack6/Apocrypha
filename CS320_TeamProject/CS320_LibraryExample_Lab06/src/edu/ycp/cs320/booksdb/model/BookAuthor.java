package edu.ycp.cs320.booksdb.model;

public class BookAuthor {

	private int bookId;
	private int authorId;
	
	public BookAuthor() {
		
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public int getAuthorId() {
		return authorId;
	}
}