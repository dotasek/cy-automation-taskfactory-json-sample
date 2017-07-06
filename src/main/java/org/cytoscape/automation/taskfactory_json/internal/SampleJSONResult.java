package org.cytoscape.automation.taskfactory_json.internal;

import org.cytoscape.work.json.JSONResult;

import com.google.gson.Gson;

public class SampleJSONResult implements JSONResult{

	private final SampleResult result;
	
	public SampleJSONResult(SampleResult result) {
		this.result = result;
	}
	
	@ExampleJSONString(value="")
	@Override
	public String getJSON() {
		Gson gson = new Gson();
		return gson.toJson(result);
	}
}