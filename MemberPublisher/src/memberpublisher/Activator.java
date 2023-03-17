package memberpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;



public class Activator implements BundleActivator {

	private ServiceRegistration memberPublisherRegistration;

	
	public void start(BundleContext context) throws Exception {
		System.out.println("Member Publisher Service Started...");
		MemberService memberPublisherService = new MemberServicelmpl();
		memberPublisherRegistration = context.registerService(MemberService.class.getName(), memberPublisherService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Member Publisher Service Stopped !");
		memberPublisherRegistration.unregister();
	}

}
