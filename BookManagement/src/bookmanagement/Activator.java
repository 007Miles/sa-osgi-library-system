package bookmanagement;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;

import bookpublisher.Book;
import bookpublisher.BookService;


public class Activator implements BundleActivator {

	ServiceReference bookServiceReference;
	private Scanner scan;

	public void start(BundleContext context) throws Exception {
		bookServiceReference = context.getServiceReference(BookService.class.getName());
		scan = new Scanner(System.in);
		
		@SuppressWarnings("unchecked")
		BookService bookService = (BookService) context.getService(bookServiceReference);
		
		boolean flag = true;
		

		
		while(flag) {
			int choice;
			System.out.println("\n\n--------------choose an option from book Management--------------");
			System.out.println("1. Insert a book");
			System.out.println("2. get Available Books");
			System.out.println("3. get All Books");
			System.out.println("4. search Book Details");
			System.out.println("5. Update book details");
			System.out.println("6. quit");
			choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice) {
			   
			   case 1:
				   bookService.insertBookDetails();
				   break;
			   case 2:
				   bookService.getAvailableBooks();
				   break;
			   case 3:
				   bookService.getAllBooks();
				   break;
			   case 4:
				   while(true) {
				   System.out.println("\n\nEnter keyword: ");
				   String keyword = scan.nextLine();
				   
				   if(keyword.isBlank()) {
					   continue;
				   }
				   else {
					   bookService.searchBookDetails(keyword);
					   break;
				   }
				   }
				   break;
			   case 5:
				   System.out.println("\nEnter the name of the book you want to update");
				   String bkname = scan.nextLine();
				   
				   bookService.editBookDetails(bkname, "");
				   break;
			   case 6:
				   flag = false;
				   break;
			   default:
				   continue;
				   
			}

		}
		
		
	
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Book Management service stopped !!!");
		context.ungetService(bookServiceReference);
		
	}

}
