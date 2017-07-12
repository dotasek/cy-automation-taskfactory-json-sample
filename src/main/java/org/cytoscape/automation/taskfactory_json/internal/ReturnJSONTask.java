package org.cytoscape.automation.taskfactory_json.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.json.JSONResult;

public class ReturnJSONTask extends AbstractTask implements ObservableTask {
	
	public ReturnJSONTask(){
		super();
	}
	
	@ProvidesTitle
	public String getTitle() { return "TaskFactory Return JSON Sample"; }

	@Tunable (description="Name", longDescription="The name to pass in the name field")
	public String name = "";
	
	private SampleResult result;
	
	@Override
	public void run(TaskMonitor arg0) throws Exception {
		result = new SampleResult();
		result.name = name;
		result.values = new ArrayList<Integer>();
		result.values.add(1);
		result.values.add(2);
		result.values.add(3);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <R> R getResults(Class<? extends R> type) {
		if (type.equals(String.class)) {
			return (R) new SampleJSONResult(result).getJSON();
		} else if (type.equals(SampleResult.class)) { //Since SampleResult isn't exported from this bundle, this is only useful in-app, and shouldn't be accessed from other apps.
			return (R) result;
		} else if (type.isAssignableFrom(JSONResult.class)) {
			return (R) new SampleJSONResult(result);
		} else {
			return null;
		}
	}

	@Override 
	public List<Class<?>> getResultClasses() {
		return Arrays.asList(String.class, SampleJSONResult.class);
	}
}
