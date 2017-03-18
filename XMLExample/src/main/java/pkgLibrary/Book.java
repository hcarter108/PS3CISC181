package pkgLibrary;

import java.util.ArrayList;
import java.util.Date;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Book {

	private String id;
	private String author;
	private String title;
	private String genre;
	private double price;
	private Date publish_date;
	private String description;
	private double cost;

	public Book() {

	}

	public Book(String id, String author, String title, String genre, double cost ,double price, Date publish_date,
			String description) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.cost= cost;
		this.price = price;
		this.publish_date = publish_date;
		this.description = description;
	}

	public Book(String id) throws BookException {
		super();
		Book b= getBook(id);
		this.id = b.getId();
		this.author = b.getAuthor();
		this.title = b.getTitle();
		this.genre = b.getGenre();
		this.cost=b.getCost();
		this.price = b.getPrice();
		this.publish_date = b.getPublish_date();
		this.description = b.getDescription();

	}

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	@XmlElement
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublish_date() {
		return publish_date;
	}

	@XmlElement
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	@XmlElement
	public void setCost(Double cost) {
		this.cost = cost;
	}

	public static Book getBook(String id) throws BookException {
		Catalog cat = ReadXMLFile();
		for (Book b : cat.getBooks()) {
			if (b.getId().equals(id)) {
				return b;
			}
		}
		throw new BookException(Book.getBook(id), "This book is not in the Catalog");
	}

	public static void AddBook(String id, Book b) throws BookException
	{
		Catalog cat= ReadXMLFile();
		ArrayList<Book> newlist = cat.getBooks();
		for (Book b2 : cat.getBooks()) {
			if (b2.getId().equals(id)) {
				System.out.println("Book" + id + "is already in catalog");
				throw new BookException(b,"Book is already in catalog");
			}
		}
		newlist.add(b);
		cat.setBooks(newlist);
		WriteXMLFile(cat);

	}

	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}

	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}



}
