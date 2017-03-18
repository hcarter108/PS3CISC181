package unitTestsBook;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.BookException;

// A test for getBook, one for addBook, one for BookException

public class unitTest {

@Test
public void testGetBook() throws BookException
{
	Book b = new Book("bk101");
	
	assertEquals(Book.getBook("bk101").getId(),b.getId());
	
}

// If this test is run twice without changing the id, the test will fail since the book now exists and will throw an exception, also Maven clean install does this too when running test
@Test
public void testAddBook() throws BookException
{
	Book b= new Book("bk123", "Me", "A title", "Mystery", 8.00 ,10.00, new Date(), "A test");
	Book.AddBook("bk123", b);
	assertEquals(Book.getBook("bk123").getId(), b.getId());

}


@Test (expected= BookException.class)
public void testAddBook2() throws BookException
{
	
	Book.AddBook("bk102", Book.getBook("bk102"));

}
}
