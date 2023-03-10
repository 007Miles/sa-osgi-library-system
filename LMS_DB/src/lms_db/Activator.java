package lms_db;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

//	private static BundleContext context;
	private ServiceRegistration serviceRegistration;


//	static BundleContext getContext() {
//		return context;
//	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("LMS DB Publisher service started...");
		Database database = new DatabaseImpl();
		serviceRegistration = bundleContext.registerService(Database.class.getName(), database, null);
	
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("LMS DB Publisher service stopped !!!");
		serviceRegistration.unregister();
	}

}
