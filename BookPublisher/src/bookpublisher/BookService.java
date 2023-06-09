package bookpublisher;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface BookService {
	public void insertBookDetails();
	public void getAvailableBooks();
	
 	public void getAllBooks();
	public void searchBookDetails(String keyword);
//	public boolean getAvailability(String name);
//	public void changeAvailability(String name);
	
	public boolean getBookAvailabilityById(Integer id);
	

	
	public List<Book> getBookListByClass();
	public void editBookDetails(Integer id, String command); 
	public List<Book> getBooksbyFile();
}
