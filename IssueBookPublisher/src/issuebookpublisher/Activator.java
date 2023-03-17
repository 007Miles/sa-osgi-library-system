package issuebookpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Issue Book Publisher Started");
		IssueBookPublish publisherService = new IssueBookPublishImpl();
		publishServiceRegistration = context.registerService(IssueBookPublish.class.getName(), publisherService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Issue Book Publisher Stopped");
		publishServiceRegistration.unregister();
	}

}
