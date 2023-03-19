package bookpublisher;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;



public class BookServiceImpl implements BookService {

	private static Scanner scan;
	private ArrayList<String[]> list;
	private HashMap<Integer, String> bookAvailability;
	
	private static final String FILE_NAME = "C:\\Users\\miyur\\eclipse-workspace_02\\BookPublisher\\src\\bookpublisher\\book_data.txt";

	
	private List<Book> bookList = new ArrayList<>();
	
	public BookServiceImpl() {
		
		scan = new Scanner(System.in);
		list = new ArrayList<String[]>();
		bookAvailability = new HashMap<Integer, String>();

	}
	
	
	@Override
	public void insertBookDetails() {
		String bookName, authorName,  genre, isbnNumber;
		String availability; Integer bookID;
		
		System.out.println("\n------- Insert New Book To System --------\n");
		System.out.print("Enter books's name: ");
		bookName = scan.nextLine();
		System.out.print("Enter author's name: ");
		authorName = scan.nextLine();
		System.out.print("Enter genre: ");
		genre = scan.nextLine();
		System.out.print("Enter ISBN number: ");
		isbnNumber = scan.nextLine();
		
//		System.out.print("Enter availability: ");
		availability = "available";
//		scan.nextLine();
		
		
	    bookID = getNextId();
		
	
//		String tempBook[] = {bookName,authorName, genre, isbnNumber, availability};
		Book newBook = new Book(bookID,bookName,authorName,genre,isbnNumber,availability);
		
		try(BufferedWriter writer  = new BufferedWriter(new FileWriter(FILE_NAME,true));) {
			
			writer.write(newBook.getBookId()+"," +newBook.getBookName()+","+ newBook.getAuthorName()+","+ newBook.getGenre()+","+ newBook.getIsbnNUmber()
			+"," + newBook.getAvailability() + "\n" );
			
//			System.out.println(newBook.getBookName());
//			System.out.println(newBook.getAvailability());
			
			
			this.bookAvailability.put(bookID, availability);
			
			System.out.println("\nNew Book added successfully....\n");
			writer.close();
			
		}catch(IOException e){
			System.out.println("Writing error: " + e.getMessage());
			
		}
		
		
//		this.list.add(tempBook);
//		this.bookAvailability.put(bookName.toLowerCase(), availability);
		
		
//		Book book = new Book(bookName, authorName, isbnNumber, isbnNumber, availability);
		
		
	}
	
	 private int getNextId()  {
	        int id = 1;
	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                int currentId = Integer.parseInt(parts[0]);
	                if (currentId >= id) {
	                    id = currentId + 1;
	                }
	            }
	        }
	        catch(Exception e) {
	        	System.out.println("Next ID: " + e.getMessage());
	        }
	        return id;
	    }
	
	

	public List<Book> getBookListByClass() {
		return bookList;
	}


	@Override
	public void getAvailableBooks() {
		// TODO Auto-generated method stub
		int count = 0;
		List<Book> bookListtoAvailabale = this.getBooksbyFile();
		
		System.out.println("");
		
		for(Book book: bookListtoAvailabale) {
			if(book.getAvailability().equals("available")) {
				System.out.println(count+1 + ". " + book.getBookName());
				count++;
				
			}
		}
		
//		for(int i=0; i<list.size(); i++) {
//			if(Integer.valueOf(list.get(i)[4]) == 1 ) {
//				System.out.println(count + ". " + list.get(i)[0]);
//				count++;
//			}
//		}
		
	}

	@Override
	public void getAllBooks() {
		// TODO Auto-generated method stub
		int count = 0;
		List<Book> bookListtoAvailabale = this.getBooksbyFile();
		
		for(Book book: bookListtoAvailabale) {
			
				System.out.println(count +1+ ". " + book.getBookName());
				count++;
				
		}
		
	}

	@Override
	public void searchBookDetails(String keyword) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Book> bookListtoAvailabale = this.getBooksbyFile();
		
		
		
		for(Book book: bookListtoAvailabale) {
			if(book.getBookName().contains(keyword) || book.getAuthorName().contains(keyword) 
					|| book.getGenre().contains(keyword) || book.getIsbnNUmber().contains(keyword)) {
				
				
				
				System.out.println(count +1 + ". Name:" + book.getBookName()
				+" author:"+book.getAuthorName() + " genre:"+ book.getGenre()+" availability:" + book.getAvailability()) ;
				count++;
				
			}
		}
		
		if(count==0) {
			System.out.println("\nNo matches found.");
		}
		
//		for(int i=0; i<list.size(); i++) {
//			if(list.get(i)[0].contains(keyword) ||  list.get(i)[1].contains(keyword)) {
//				System.out.println(count + ". " + list.get(i)[0]);
//				count++;
//			}
//		}
		
		
	}

//	@Override
//	public boolean getAvailability(String name) {
//		// TODO Auto-generated method stub
//		if(this.bookAvailability.containsKey(name) && this.bookAvailability.get(name)=="available") {
//			return true;		
//		}
//		
//		return false;
//	}

//	@Override
//	public void changeAvailability(String name) {
//		// TODO Auto-generated method stub
//		if(this.bookAvailability.containsKey(name) && this.bookAvailability.get(name)=="available") {
//			this.bookAvailability.put(name, "not available");		
//		}
//		else if(this.bookAvailability.containsKey(name) && this.bookAvailability.get(name)=="not available") {
//			this.bookAvailability.put(name, "available");
//			
//		}
//		
//		
//	}
	
	public void changeAvailabilityList(){
		List<Book> bookListA = this.getBooksbyFile();
		this.bookAvailability.clear();
		
		for(Book book:bookListA) {
			this.bookAvailability.put(book.getBookId(), book.getAvailability());
		}
	}
	
	public String changeString(String name) {
		if(name.equals("available")) {
			return "not available";
		}
		else {
			return "available";
		}
	}
	
	
	
	
	public void editBookDetails(Integer id, String command) {
//		File oldfile = new File(FILE_NAME);
//		File newfile = new File("C:\\Users\\miyur\\eclipse-workspace_02\\BookPublisher\\src\\bookpublisher\\temp.txt");
		List<Book> bookListtoUpdate = this.getBooksbyFile();
//		String bName = ""; String bAuthor = ""; String bGenre = ""; String bisbn = "";
//		String bAvail = "";
//		boolean result;
		Scanner scan2 = new Scanner(System.in);
		
		String tbName = ""; String tbAuthor = ""; String tbGenre = ""; String tbisbn = "";
		
		try(BufferedWriter writer  = new BufferedWriter(new FileWriter(FILE_NAME,false));) {
			
			for(Book book: bookListtoUpdate) {
				
				if(book.getBookId().equals(id)) {
					
					if(command.equals("CHANGE-AVAIL-ORDER")) {
						book.setAvailability(changeString(book.getAvailability()));
					}
					else {
						System.out.println("Enter new name: ");
						tbName = scan2.nextLine();
						System.out.println("Enter new author name: ");
						tbAuthor = scan2.nextLine();
						System.out.println("Enter new genreS: ");
						tbGenre = scan2.nextLine();
						System.out.println("Enter new isbn number: ");
						tbisbn = scan2.nextLine();
					}
					
					writer.write(book.getBookId()+","+tbName+","+ tbAuthor+","+ tbGenre+","+ tbisbn
					+"," + book.getAvailability()+"\n" );
					
					System.out.println("\nProcess executed successfully....\n");
					
										
				}
				else {
				
				writer.write(book.getBookId()+"," + book.getBookName()+","+ book.getAuthorName()+","+ book.getGenre()+","+ book.getIsbnNUmber()
				+"," + book.getAvailability() + "\n" );
				}
			}
			
			
			
//			System.out.println(newBook.getBookName());
//			System.out.println(newBook.getAvailability());
			
			
//			this.bookAvailability.put(bookName.toLowerCase(), availability);
			
			
			writer.close();
			
		}catch(IOException e){
			System.out.println("Writing error: " + e.getMessage());
			
		}
		
	}



	@SuppressWarnings("finally")
	@Override
	public List<Book> getBooksbyFile() {
		// TODO Auto-generated method stub
		List<Book> bookList = new ArrayList<>();
		
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
            	
                String[] parts = line.split(",");
//                int id = Integer.parseInt(parts[0]);
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String author = parts[2];
                String genre = parts[3];
                String isbn = parts[4];
                String availability = parts[5];
                Book book = new Book(id,name, author,genre,isbn, availability);
                
//                System.out.println(book.getBookName());
                bookList.add(book);
            }
        } catch (IOException e) {
            System.err.println("Error reading book data from file: " + e.getMessage());
        }finally {
        	return bookList;
        }     
		
	}


	@Override
	public boolean getBookAvailabilityById(Integer id) {
		// TODO Auto-generated method stub
		this.changeAvailabilityList();
		
		if(this.bookAvailability.containsKey(id) && this.bookAvailability.get(id).equals("available")) {
			return true;
		}
		
		return false;
	}

}
