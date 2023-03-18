package membermanagement;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;




import java.util.List;
import java.util.Scanner;

import memberpublisher.Member;
import memberpublisher.MemberService;
import staffpublisher.StaffService;


public class Activator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceReference memberServiceReference, staffServiceReference;
	private Scanner scan;

	
	public void start(BundleContext context) throws Exception {
		
		memberServiceReference = context.getServiceReference(MemberService.class.getName());
		scan = new Scanner(System.in);
		
		@SuppressWarnings("unchecked")
		MemberService memberPublisher = (MemberService) context.getService(memberServiceReference);
		
		staffServiceReference = context.getServiceReference(StaffService.class.getName());
		@SuppressWarnings("unchecked")
		StaffService staffService = (StaffService) context.getService(staffServiceReference);
		
		
		while(true) {
			int choice;
			System.out.println("\n--------------Choose an option from Member Management--------------");
			System.out.println("1. Insert a New Member");
			System.out.println("2. Get All Memeber Details");
			System.out.println("3. Search Member Details");
			System.out.println("4. Update Member Details");
			System.out.println("5. Delete Member");
			System.out.println("6. quit");
			choice = scan.nextInt();

			scan.nextLine();
			
			switch(choice) {
			   
			   case 1:
				   System.out.println("\nPlease Enter your staff ID to continue : ");
				   int staffid = scan.nextInt();
				   
				   if(staffService.getAvailability(staffid) == true) {
					   memberPublisher.insertMemberDetails();
				   }
				   else {
					   System.out.println("\n You have Entered invalid Staff ID");
				   }
				   
				   break;
			   case 2:
				   List<Member> memberList = memberPublisher.getMemberAll();
               	for (Member member : memberList) {
               	System.out.println("-> " + "Member ID :" + member.getMemberId()+ "| First Name : "  + member.getFirstName() + "| Last Name : " + member.getLastName()+ "| DOB : "  + 
               	member.getDob() + "| NIC : " + member.getNic() + "| PHone Number : " + member.getPhoneNumber()+ "| Email : " + member.getEmail()+ " | Address :" + member.getAddress()  );
               	}
               	
				   break;
			   case 3:
				   while(true) {
					   System.out.println("\n\nEnter keyword to Search : ");
					   String keyword = scan.nextLine();
					   
					   if(keyword.isBlank()) {
						   continue;
					   }
					   else {
						   memberPublisher.searchMemberDetails(keyword);
						   break;
					   }
					   }

				   break;
			   case 4:
				   System.out.println("\nEnter the NIC of the Member you want to Update Details");
				   String nic = scan.nextLine(); 
				   memberPublisher.editMemberDetails(nic);
				   break;
				   
			   case 5:
				   System.out.println("\nEnter the NIC of the Member you want to Delete");
				   String dnic = scan.nextLine();
				   
				   memberPublisher.deleteMember(dnic);
				   break;
				
			   case 6:
				   System.out.println("Exiting...");
				   System.exit(0);
				   break;
				   
			   default:
				   continue;
				   
			}
		}
			
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Member Management service stopped !");
		context.ungetService(memberServiceReference);
	}

}
