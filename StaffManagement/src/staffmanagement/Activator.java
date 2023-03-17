package staffmanagement;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import staffpublisher.Staff;
import staffpublisher.StaffService;

public class Activator implements BundleActivator {

	 
	ServiceReference staffServiceReference;
	private Scanner scanner;
//	private static BundleContext context;
//
//	static BundleContext getContext() {
//		return context;
//	}

	public void start(BundleContext bundleContext) throws Exception {
//		Activator.context = bundleContext;
		
		staffServiceReference = bundleContext.getServiceReference(StaffService.class.getName());
		scanner = new Scanner(System.in);
		
		
		@SuppressWarnings("unchecked")
		StaffService staffService = (StaffService) bundleContext.getService(staffServiceReference);
		
        while (true) {
            System.out.println("Library Staff Management System");
            System.out.println("1. List all staff");
            System.out.println("2. Add new staff");
            System.out.println("3. Update staff");
            System.out.println("4. Delete staff");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1	:	
                	List<Staff> staffList = staffService.getAllStaff();
                	for (Staff staff : staffList) {
                	System.out.println(staff);
                	}
                	break;
                case 2:
                    staffService.addStaffFromConsole();
                    break;

                case 3:
                    staffService.updateStaffFromConsole();
                    break;

                case 4:
                    staffService.deleteStaffFromConsole();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Staff Management service stopped !!!");
		bundleContext.ungetService(staffServiceReference);
	}

}
