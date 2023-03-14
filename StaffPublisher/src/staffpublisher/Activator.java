package staffpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

//	private static BundleContext context;

	private ServiceRegistration staffServiceRegistration;
	
//	static BundleContext getContext() {
//		return context;
//	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Staff Service Started...");
		StaffService staffService = new StaffServiceImpl();
		staffServiceRegistration = bundleContext.registerService(StaffService.class.getName(), staffService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Staff Service Stopped !!! ");
		staffServiceRegistration.unregister();
	}

}
