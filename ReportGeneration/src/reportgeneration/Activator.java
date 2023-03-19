package reportgeneration;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import bookpublisher.Book;
import bookpublisher.BookService;
import issuebookpublisher.IssueBookPublish;
import memberpublisher.MemberService;
import memberpublisher.Member;
import staffpublisher.Staff;
import staffpublisher.StaffService;

public class Activator implements BundleActivator {
	
	ServiceReference staffServiceReference;
	ServiceReference bookServiceReference;
	ServiceReference memberServiceReference;
	ServiceReference bookIssueServiceReference;
	
	private Scanner scanner;

	@SuppressWarnings("unchecked")
	public void start(BundleContext bundleContext) throws Exception {
		
		staffServiceReference = bundleContext.getServiceReference(StaffService.class.getName());
		bookServiceReference = bundleContext.getServiceReference(BookService.class.getName());
		memberServiceReference = bundleContext.getServiceReference(MemberService.class.getName());
		bookIssueServiceReference = bundleContext.getServiceReference(IssueBookPublish.class.getName());
		scanner = new Scanner(System.in);
		
	
		StaffService staffService = (StaffService) bundleContext.getService(staffServiceReference);
		BookService bookService = (BookService) bundleContext.getService(bookServiceReference);
		IssueBookPublish issueBookService = (IssueBookPublish) bundleContext.getService(bookIssueServiceReference);
		MemberService memberService = (MemberService) bundleContext.getService(memberServiceReference);

		while (true) {
            System.out.println("-----------------   Library Report Generation   ---------------------");
            System.out.println("1. List all Issued Books Details");
            System.out.println("2. List all Books Details");
            System.out.println("3. List all Members Details");
            System.out.println("4. List all staff members Details ");
            System.out.println("5. Exit");
            System.out.println("---------------------------------------------------------------------------");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1	:	
                	issueBookService.viewIssuedBooks();
                	break;
                case 2:
                    List<Book> bookList = bookService.getBooksbyFile();
                	for (Book book : bookList) {
                	System.out.println(book);
                	}
                    break;

                case 3:
                	List<Member> memberList = memberService.getMemberAll();
                   	for (Member member : memberList) {
                   	System.out.println(member);
                   	}
                    break;

                case 4:
                	List<Staff> staffList = staffService.getAllStaff();
                	for (Staff staff : staffList) {
                	System.out.println(staff);
                	}
                    break;

                case 5:
                    System.out.println("Exited from the system");
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("---------------------------------------------------------------------------");
                    break;
            }

            System.out.println();
        }

		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Report generation service stopped...");
		bundleContext.ungetService(staffServiceReference);
		bundleContext.ungetService(bookIssueServiceReference);
		bundleContext.ungetService(bookServiceReference);
		bundleContext.ungetService(memberServiceReference);
		
	}

}
