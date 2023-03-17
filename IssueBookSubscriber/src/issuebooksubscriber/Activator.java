package issuebooksubscriber;

import issuebookpublisher.IssueBookPublish;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	
	ServiceReference serviceReference;
	private Scanner sc;

	public void start(BundleContext context) throws Exception {
		System.out.println("\nStart Issue Book Subscriber Service \n");
		serviceReference = context.getServiceReference(IssueBookPublish.class.getName());
		sc = new Scanner(System.in);
		IssueBookPublish issueBookPublish = (IssueBookPublish) context.getService(serviceReference);
		System.out.println(issueBookPublish.publishIssueBookService());
		System.out.println();
		
		while (true) {
			int input;
			System.out.println("\n\t---------Choose a number to issue a book or to view issued books list---------\n");
			System.out.println("1. Issue a book");
			System.out.println("2. View issued books list");
			System.out.println("3. Return a Book");
			System.out.println("4. View returned books list");
			System.out.println("5. Exit");
			input = sc.nextInt();
			
			if (input == 1) {
				int iid;
				int uid;
				int bid;
				String iDate;
				String period;
				
//				while (true) {
				
				System.out.println("Enter Book ID: ");
				bid = sc.nextInt();
//				if(bookMangement.getAvailability(bid)) {
//					
				System.out.println("Enter Issue ID: ");
				iid = sc.nextInt();
				System.out.println("Enter User ID: ");
				uid = sc.nextInt();
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
				
				issueBookPublish.issueBooks(iid,uid,bid,iDate,period);
				// break;
				
//				}else {
//					System.out.println("Sorry the book requested is not available...");
//					continue;
//				}
				continue;
//			}

			}
			else if (input == 2) {
				issueBookPublish.viewIssuedBooks();
				continue;
			}
			else if (input == 3) {
				int riid;
//				int check;
				String rDate;
				int overdueDays;
				
//				while(true) {
				System.out.println("Enter Issue ID: ");
//				riid = sc.nextInt();
				/*check*/ riid = sc.nextInt();
				//check if riid value is true
				
//				if (check == cnsv) {
//					riid = check;
//					break;
//				} else {
//					System.out.println("Issue ID you entered is wrong, Try again...");
//					continue;
//				}
//				}
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
				System.out.println("Enter a valid number...");
				continue;
			}
		}
//		issueBookPublish.viewIssuedBooks();
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Issue Book Service is Completed!!!");
		context.ungetService(serviceReference);
	}

}
