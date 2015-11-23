
package DbOject;
import java.io.File;
import java.util.*;

import com.db4o.*;
import com.db4o.config.Configuration;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.config.ObjectClass;
import com.db4o.config.ObjectField;
import com.db4o.query.*;


public class Main1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1. Xoá CSDL nếu đã có
		new File("F:/db4oExample.db4o").delete();    
		// 2. Tạo mới và mở CSDL
		ObjectContainer db = Db4oEmbedded.openFile("F:/db4oExample.db4o");
		
		
                /** 3. Tạo  publication  ("Fundamentals of Database Systems", 2015) với các tác giả : 
		("Ramez Elmasri"); 
		("Shamkant B. Navathe"); **/
		
                
		Publication Pub1 = new Publication("Fundamentals of Database Systems",2015);
                Author Elmasri = new Author("Ramez Elmasri");
                Author Navathe = new Author("Shamkant B. Navathe");
                Pub1.addAuthor(Elmasri);
                Pub1.addAuthor(Navathe);
                Elmasri.addPub(Pub1);
                Navathe.addPub(Pub1);
                
		//4. Lưu vào CSDL publication vừa tạo, chi luu Pub khong luu tac gia
                db.store(Pub1);
			
		//5. Truy vấn QBE : tìm tác giả Ramez Elmasri  và cho nhận xét
			System.out.println("------- QBE : Author -----------");
			ObjectSet<Author> setAus = db.query
                        (new Predicate<Author>()
                            {
                                public boolean match(Author Au) 
                                {
                                    return Au.getName().equals("Ramez Elmasri");
                                }
                            } 
                        );

                        for (Author Au: setAus) {
                        System.out.println(Au.getName());
                        }
			//=======================================
                        //Dap An: return 1 dong Ramez Elmasri
                        //=======================================
                        
			//6. Hiển thị tất cả Publication 
			System.out.println("----------- QBE : All Publications ---------------");
			ObjectSet<Publication> pubs = db.queryByExample(Publication.class);
                        for (Publication pub: pubs) {
                        System.out.println(pub.getTitle()+"\t"+pub.getYear());}
                        //System.out.println(Person);}
                        System.out.println("---------------------");
			
			// Native
			System.out.println("---------native & simple  & comparision ------------");
			/** 7. Thêm vào CSDL 2 publication 
                         "Zend Framework 1 to 2 Migration Guide: a php[architect] guide 2015 - 
                            Tac gia : Bart McLeod va Eli White" 
			 "Big Data For Dummies 2013- 
                            Tac gia: Arun Murthy va Vinod Vavilapalli" **/
			
                        Publication Pub2 = new Publication("Zend Framework 1 to 2 Migration Guide: a php[architect] guide",2015);
                        Author McLeod = new Author("Bart McLeod");
                        Author White = new Author("Eli White");
                        Pub2.addAuthor(McLeod);
                        Pub2.addAuthor(White);
                        db.store(Pub2);
                        
                        Publication Pub3 = new Publication("Big Data For Dummies",2013);
                        Author Murthy = new Author("Arun Murthy");
                        Author Vavilapalli = new Author("Vinod Vavilapalli");
                        Pub3.addAuthor(Murthy);
                        Pub3.addAuthor(Vavilapalli);
                        db.store(Pub3);
                        
			// 8.Tìm Tất cả Publication từ năm 1990 trở về sau và sắp xếp theo thứ tự theo tên bài báo 
			
                        //Ham sap xep thu tu tang dan
                        Comparator<Publication> personCmp=
                        new Comparator<Publication>() {
                        public int compare(Publication p1, Publication p2) {
                        return p1.getTitle().compareTo(p2.getTitle());
                        } };
                        
                        //Ham tra <=1990 order tile
                        ObjectSet<Publication> naPubs = db.query(new Predicate<Publication>() 
                        { 
                        public boolean match(Publication publication) {
                        return publication.getYear() <= 1990; 
                        } 
                        },personCmp ); 
                        
                        for (Publication publication: naPubs) {
                        System.out.println(publication.getTitle());
                        }
                        
			//9. Tìm Tất cả Publication từ năm 1990 đến năm 2000 hoặc có tựa là "Java Programming"
			System.out.println("---------native & complex  ------------");
				
			//Ham tra <=1990 order tile
                        ObjectSet<Publication> naPubs1 = db.query(new Predicate<Publication>() 
                        { 
                        public boolean match(Publication publication) {
                        return publication.getYear() >= 1990 &&
                        publication.getYear()<=2000 ||
                        publication.getTitle().equals("Java Programming");
                        } 
                        },personCmp ); 
                        
                        for (Publication publication: naPubs1) {
                        System.out.println(publication.getTitle());
                        }	
			
			/***** SODA****/
			/*
                        – Thêm hoặc duyệt qua một nút trong cây truy vấn với
                        descend()
                        – Thêm một ràng buộc (điều kiện) tới một nút dùng
                        constrain()
                        – Có thể sắp xếp kết quả SortBy() tăng dần
                        orderAscending() hoặc giảm dần orderDescending()
                        – Thực thi câu truy vấn execute()
                        – So sánh lớn hơn greater() và nhỏ hơn smaller()
                        – Biểu thức bằng equal() và like()
                        – Các phép toán and, or và not
                        – Các hàm xử lý chuỗi với startsWith() và endsWith()
                        – Kiểm tra một phần tử thuộc tập hợp với hàm
                        contains()
                        */
                        
                        
			System.out.println("---------SODA------------");
			// 10.  Tìm Tất cả Publication từ năm 1990 và có tựa là "Java Programming"
			/*
                        System.out.println("---------SODA - Tìm Tất cả Publication từ năm 1990 ------------");
                        Query query = db.query();
                        query.constrain(Publication.class); 
                        query.descend("year").constrain(Integer.valueOf(1990)).greater(); //Tìm Tất cả Publication từ năm 1990                         
                        ObjectSet<Publication> publications = query.execute(); 
                        for (Publication publication : publications) { 
                        System.out.println(publication.getTitle()); }
                        */
                        /*
                        System.out.println("---------SODA - Tìm Tất cả Publication từ năm 1990 va co tua de Java Programming------------");
                        Query query1 = db.query(); 
                        query1.constrain(Publication.class); 
                        Publication Pubtitle = new Publication("Fundamentals of Database Systems"); 
                        Constraint constr= 
                        query1.descend("year").constrain(Integer.valueOf(1990)).greater();
                        query1.descend("title").constrain(Pubtitle).contains().and(constr); 
                        ObjectSet<Publication> publications1 = query1.execute(); 
                        for (Publication publication : publications1) { 
                        System.out.println(publication.getTitle()); }
                        */
                        //getTitle().equals("Java Programming");
                        /*
                        Query query = db.query(); 
                        query.constrain(Publication.class); 
                        Publication Pubtitle = new Publication("Fundamentals of Database Systems"); 
                        //query.descend("year").constrain(Integer.valueOf(1990)).greater();
                        query.descend("title").constrain(Pubtitle).contains(); 
                        ObjectSet<Publication> publications = query.execute(); 
                        
                        for (Publication publication : publications) { 
                        System.out.println(publication.getTitle()); }
                        */
                        
                        /*
                        Query query = db.query(); 
                        query.constrain(Publication.class); 
                        Author proto = new Author("Ramez Elmasri"); 
                        query.descend("authors").constrain(proto).contains(); 
                        ObjectSet<Publication> publications = query.execute(); 
                        for (Publication publication : publications) { 
                        System.out.println(publication.getTitle()); }
                        */
                        
                        /*
			Query query = db.query(); 
                        query.constrain(Publication.class); 
                        Author proto = new Author("Moira C. Norrie"); 
                        query.descend("authors").constrain(proto).contains(); 
                        ObjectSet<Publication> publications = query.execute(); 
                        for (Publication publication : publications) { 
                        System.out.println(publication.getTitle()); }
                                               
                        Query query = db.query(); 
                        query.constrain(Publication.class); 
                        Author proto = new Author("Moira C. Norrie"); 
                        Constraint constr= 
                        query.descend("year").constrain(Integer.valueOf(1995)).greater();
                        query.descend("authors").constrain(proto).contains().and(constr); 
                        ObjectSet<Publication> publications = query.execute(); 
                        for (Publication publication : publications) { 
                        System.out.println(publication.getTitle()); }
                        */
                        
			/**** UPDATE SIMPLE************/
			//11. Cập nhật lại ngày sinh cho tác giả  Ramez Elmasri là 20/10/1956
			
                        Author au = (Author) db.queryByExample(new
                        Author("Ramez Elmasri")).next();
                        Calendar calendar = Calendar.getInstance(); 
                        calendar.set(1956, Calendar.OCTOBER, 20); 
                        au.setBirthday(calendar.getTime()); 
                        db.store(au);
                        
			//12. HIển thị kết quả cập nhật
			//Hiển thị tất cả Author 
			System.out.println("----------- QBE : All Author ---------------");
			ObjectSet<Author> Aus = db.queryByExample(Author.class);
                        for (Author Au: Aus) {
                        System.out.println(Au.getName()+"\t"+Au.getBirthday());}
                        //System.out.println(Person);}
                        System.out.println("---------------------");
                        
			/********  UPDATE COMPLEX**************/
			//13. Cap nhat lai ngay sinh cho tác giả Ramez Elmasri 20/10/1945 và năm xuất bản cho tất cả các publication là 2008
			System.out.println("---------UPDATE COMPLEX ------------");
			/*
                        Author au1 = (Author) db.queryByExample(new Author("Ramez Elmasri")).next();
                        Calendar calendar1 = Calendar.getInstance(); 
                        calendar1.set(1945, Calendar.OCTOBER, 20); 
                        au1.setBirthday(calendar1.getTime()); 
                        db.store(au1);
                        */

                        Author au2 = (Author) db.queryByExample(new Author("Ramez Elmasri")).next();
                        Calendar calendar1 = Calendar.getInstance(); 
                        calendar1.set(1945, Calendar.OCTOBER, 20); 
                        au2.setBirthday(calendar1.getTime()); 
                        db.store(au2);

                        // cập nhật tất cả các publications
                        Author au13 = (Author) db.queryByExample(new Author("Ramez Elmasri")).next();                       
                        for (Publication publication: au13.getPubs()) { 
                        publication.setYear(2008); }
                        // Lưu lại tác giả không có bất kỳ thay đổi nào đến các publications 
                        db.store(au13); 
                        // Lưu lại các publication đã cập nhật
                        for (Publication publication: au13.getPubs()) { 
                        db.store(publication); }
                        
                        Author vd = (Author) db.queryByExample(new Author("Ramez Elmasri")).next(); 
                        for (Publication publications: vd.getPubs()) {
                            publications.setYear(2007); }
                        db.store(vd);
                        
                        
                        
			//14. In kết quả gồm tên năm sinh  tựa và năm xuất bản các pub của tác giả vừa cập nhật để kiểm tra 
                        
                        //Hiển thị tất cả Author 
			System.out.println("----------- QBE : All Author ---------------");
			ObjectSet<Author> Aus1 = db.queryByExample(Author.class);
                        for (Author Au: Aus1) {
                        System.out.println(Au.getName()+"\t"+Au.getBirthday());}
                        //System.out.println(Person);}
                        System.out.println("---------------------");
                        
                        //Hiển thị tất cả Publications
                        System.out.println("----------- QBE : All Publications ---------------");
			ObjectSet<Publication> pubs3 = db.queryByExample(Publication.class);
                        for (Publication pub: pubs3) {
                        System.out.println(pub.getTitle()+"\t"+pub.getYear());}
                        //System.out.println(Person);}
                        System.out.println("---------------------");
                        
			//15. Đóng CSDL sau đó mở lại
                        db.close();
                        ObjectContainer db1 = Db4oEmbedded.openFile("F:/db4oExample.db4o");
			// 16. Làm lại câu 14 và nhận xét
			System.out.println("----------- QBE : All Author ---------------");
			ObjectSet<Author> Aus16 = db1.queryByExample(Author.class);
                        for (Author Au: Aus16) {
                        System.out.println(Au.getName()+"\t"+Au.getBirthday());}
                        //System.out.println(Person);}
                        System.out.println("---------------------");
                        
                        //Hiển thị tất cả Publications
                        System.out.println("----------- QBE : All Publications ---------------");
			ObjectSet<Publication> pubs16 = db1.queryByExample(Publication.class);
                        for (Publication pub: pubs16) {
                        System.out.println(pub.getTitle()+"\t"+pub.getYear());}
                        //System.out.println(Person);}
                        System.out.println("---------------------");
                        
			 //17. Đặt lại cấu hình cho phép cập nhật cascade. 
                        //EmbeddedConfiguration config = Db4oEmbedded.newConfiguration(); 
                        //config.common().objectClass(Author.class).cascadeOnUpdate(true);
                        
			 // 18. Chạy lại chương trình để kiểm tra cập nhật cascade
                        //ObjectContainer db = Db4oEmbedded.openFile("D:/db4oExample.db4o");
                        //ObjectContainer db18 = Db4oEmbedded.openFile(config,"D:/db4oExample.db4o");
                        //Author Del_Elmasri = (Author)db18.queryByExample(new Author("Ramez Elmasri")).next();
                        //db.delete(Del_Elmasri);
                        
                        /*
                        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration(); 
                        config.common().objectClass(Author.class).cascadeOnUpdate(true); 
                        ObjectContainer db2 = Db4oEmbedded.openFile(config, "F:/db4oExample.db4o");
                        Author Au0 = (Author)db2.queryByExample(new Author("Ramez Elmasri")).next(); 
                        for (Publication publications: Au0.getPubs()) { 
                            publications.setYear(2007); 
                        }
                        db2.store(Au0);
                        */
                        
			
                        /*
                        System.out.println("----------- QBE : All Author ---------------");
			ObjectSet<Author> Aus18 = db18.queryByExample(Author.class);
                        for (Author Au: Aus18) {
                        System.out.println(Au.getName()+"\t"+Au.getBirthday());}
                        //System.out.println(Person);}
                        System.out.println("---------------------");
                        */
                        
			 //19. Thêm hai class java định nghĩa cho Article và Book kế thừa Publication như hình trong phần giới thiệu db4O 
			// 20.Thêm Quyển sách "Gone with the wind", tác giả  Margaret Mitchell, năm xuất bản 2011, Giá 12.98 
			//21. Kiểm tra rằng tác giả và publication cũng được lưu
            //db.close();
	}
	
	
	
}
