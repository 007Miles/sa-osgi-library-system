package issuebooksubscriber;

import issuebookpublisher.IssueBookPublish;
import memberpublisher.MemberService;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import bookpublisher.BookService;
import staffpublisher.StaffService;

public class Activator implements BundleActivator {
	
	ServiceReference serviceReference, sr, memberReference, bookReference;
	
	 
	private Scanner sc;

	public void start(BundleContext context) throws Exception {
		System.out.println("\nStart Issue Book Subscriber Service \n");
		
		serviceReference = context.getServiceReference(IssueBookPublish.class.getName());
		sr = context.getServiceReference(StaffService.class.getName());
		bookReference = context.getServiceReference(BookService.class.getName());
		memberReference = context.getServiceReference(MemberService.class.getName());
		
		sc = new Scanner(System.in);
		
		IssueBookPublish issueBookPublish = (IssueBookPublish) context.getService(serviceReference);
		StaffService staffService = (StaffService) context.getService(sr);
		BookService book = (BookService) context.getService(bookReference);
//		MemberService member = (MemberService) context.getService(memberReference);
		
		System.out.println(issueBookPublish.publishIssueBookService());
		System.out.println();
		
		while (true) {
			int input;
			System.out.println("\n\t---------Choose a number to gain access to issue book and return book operations---------\n");
			System.out.println("1. Issue a book");
			System.out.println("2. View issued books list");
			System.out.println("3. Return a Book");
			System.out.println("4. View returned books list");
			System.out.println("5. Exit");
			input = sc.nextInt();
			
			if (input == 1) {
				int iid;
				int sid;
				int uid;
				int bid;
				String iDate;
				String period;
				
				while (true) {
					System.out.println("Enter Issue ID: ");
					iid = sc.nextInt();
					System.out.println("Enter Book ID: ");
					bid = sc.nextInt();
					
					if(book.getBookAvailabilityById(bid) == true) {
					
						System.out.println("Enter Staff in Charge ID: ");
						sid =sc.nextInt();
				
						if (staffService.getAvailability(sid) == true) {
					
							System.out.println("Enter User ID: ");
							uid = sc.nextInt();
							
//							if (member.getAvailabilityByID(uid) == true) {
								System.out.println("Enter Issue Date: ");
								iDate = sc.next();
								System.out.println("Enter Borrowing Period: ");
								period = sc.next();
								System.out.println();
				
								issueBookPublish.setIid(iid);
								issueBookPublish.setUid(uid);
								issueBookPublish.setBid(bid);
								issueBookPublish.setiDate(iDate);
								issueBookPublish.setPeriod(period);
								issueBookPublish.setSid(sid);
				
								issueBookPublish.issueBooks(iid,uid,bid,iDate,period,sid);
								break;
//							} else {
//								System.err.println("Sorry, There is no member registered in the library with the given ID. Try again with valid info...");
//								continue;
//							}
						} else {
							System.err.println("Sorry the staff ID of the staff in charge of the book issuing process is not valid. Try again with valid info...");
							continue;
						}
					}else {
						System.err.println("Sorry the book requested is not available. Try again with valid info...");
						continue;
					}
			}

			}
			else if (input == 2) {
				issueBookPublish.viewIssuedBooks();
				continue;
			}
			else if (input == 3) {
				int riid;
				String rDate;
				int overdueDays;
				
				System.out.println("Enter Issue ID: ");
				riid = sc.nextInt();
				System.out.println("Enter Return Date: ");
				rDate = sc.next();
				System.out.println("Enter No. of Overdue Days: ");
				overdueDays = sc.nextInt();
				
				issueBookPublish.returnBooks(riid, rDate, overdueDays);
				continue;
			}
			else if (input == 4) {
				issueBookPublish.viewReturnBooks();
				continue;
			}
			else if (input == 5) {
				System.exit(0);
				break;
			}
			else {
				System.err.println("Enter a valid number...");
				continue;
			}
		}
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Issue Book Service is Completed!!!");
		context.ungetService(serviceReference);
	}

}
