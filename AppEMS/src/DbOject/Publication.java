package DbOject;
import java.util.*;


public class Publication {
	private String title; 
	private int year;
	private List<Author> authors;
	
	public Publication(String title) { 
		this.title= title; 
		this.authors = new ArrayList<Author>(); 
	}
	public Publication(String title, int year) { 
		this.title= title; 
		this.year=year;
		this.authors = new ArrayList<Author>(); 
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	// phương thức addAuthor(Author author) để thêm các tác giả cho một Publication  
	
        public boolean addAuthor(Author author) {
		//...
            return this.authors.add(author);
	}
        
}
