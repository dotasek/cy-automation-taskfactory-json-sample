package org.cytoscape.automation.taskfactory_json.internal;


import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

import static org.cytoscape.work.ServiceProperties.COMMAND;
import static org.cytoscape.work.ServiceProperties.COMMAND_DESCRIPTION;
import static org.cytoscape.work.ServiceProperties.COMMAND_NAMESPACE;
import static org.cytoscape.work.ServiceProperties.IN_CONTEXT_MENU;
import static org.cytoscape.work.ServiceProperties.IN_MENU_BAR;
import static org.cytoscape.work.ServiceProperties.PREFERRED_MENU;
import static org.cytoscape.work.ServiceProperties.TOOLTIP;

import java.util.Properties;

import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.work.TaskFactory;

public class CyActivator extends AbstractCyActivator {

	public static final String SAMPLE_COMMAND_NAMESPACE = "sample_app";

	public CyActivator() {
		super();
	}

	public void start(BundleContext bc) throws InvalidSyntaxException 
	{
		String returnAValueDescription = "Return a JSON object";
		
		Properties returnAValueTaskFactoryProperties = new Properties();
		returnAValueTaskFactoryProperties.setProperty(COMMAND_NAMESPACE, SAMPLE_COMMAND_NAMESPACE);
		returnAValueTaskFactoryProperties.setProperty(COMMAND, "return_json");
		returnAValueTaskFactoryProperties.setProperty(COMMAND_DESCRIPTION,  returnAValueDescription);
		returnAValueTaskFactoryProperties.setProperty(PREFERRED_MENU, "Sample App");
		returnAValueTaskFactoryProperties.setProperty(IN_MENU_BAR, "true");
		returnAValueTaskFactoryProperties.setProperty(IN_CONTEXT_MENU, "false");
		returnAValueTaskFactoryProperties.setProperty("title", "Return a JSON Value");
		returnAValueTaskFactoryProperties.setProperty(TOOLTIP,  returnAValueDescription);

		TaskFactory returnAValueTaskFactory = new ReturnJSONTaskFactory();
		registerAllServices(bc, returnAValueTaskFactory, returnAValueTaskFactoryProperties);
	}
}

