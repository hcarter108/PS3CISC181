package pkgLibrary;

public class BookException extends Exception {
	private Book b;
	private String id;
	
	public BookException(Book b, String message)
	{
		super(message);
		this.b=b;
	}
	
}
