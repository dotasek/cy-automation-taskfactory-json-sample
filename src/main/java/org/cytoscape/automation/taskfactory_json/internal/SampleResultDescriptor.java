package org.cytoscape.automation.taskfactory_json.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.cytoscape.work.ResultDescriptor;
import org.cytoscape.work.json.JSONResult;

public class SampleResultDescriptor implements ResultDescriptor {
	@Override
	public List<Class<?>> getResultTypes() {
		return Collections.unmodifiableList(Arrays.asList(String.class, SampleResult.class, JSONResult.class));
	}

	private final SampleResult resultExample;
	
	public SampleResultDescriptor() {
		resultExample = new SampleResult();
	
		resultExample.name = "Hodor";
		resultExample.values = new ArrayList<Integer>();
		resultExample.values.add(1);
		resultExample.values.add(2);
		resultExample.values.add(3);
	}
	
	@Override
	public <K> K getResultExample(Class<K> type) {
	
		if (type.equals(String.class)){
			return (K) new SampleJSONResult(resultExample).getJSON();
		} else if (type.isAssignableFrom(SampleResult.class)){
			return (K) resultExample;
		} else if (type.isAssignableFrom(JSONResult.class)) {
			return (K) new SampleJSONResult(resultExample);
		}
		return null;
	}


}