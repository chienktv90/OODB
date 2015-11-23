package DbOject;
import java.util.*;
public class Author {
	
	private String name; 
	private Date birthday; 
	private Set<Publication> pubs;
	
	public Author() {
	}
	
    public Author(String name) {
		
		this.name = name;
		
		this.pubs = new  HashSet<Publication>();
	}

    public Author(Object object, Object object0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Set<Publication> getPubs() {
		return pubs;
	}
	public void setPubs(Set<Publication> pubs) {
		this.pubs = pubs;
	}
	//// Viết phương thức addPub(Publication author) để thêm các Publication cho một Author  
	
	
        public boolean addPub(Publication pub) {
		//....
            return this.pubs.add(pub);
	}
        
}
