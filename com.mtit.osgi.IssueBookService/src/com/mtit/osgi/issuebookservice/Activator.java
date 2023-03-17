package com.mtit.osgi.issuebookservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Issue Book Service");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Issue Book Service");
	}

}
