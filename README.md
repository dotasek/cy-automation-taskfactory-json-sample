# TaskFactory JSON Result Sample

This sample creates a TaskFactory instance that provides JSON results. When added to the OSGi context, this TaskFactory can then be executed outside of Cytoscape through REST, and it's JSON result can be extracted.

## Prerequisites

It is recommended to be familiar with concepts in [Cytoscape 3.0 App Development](http://wiki.cytoscape.org/Cytoscape_3/AppDeveloper), as well as the [Cytoscape Automation TaskFactory Sample App](https://github.com/cytoscape/cytoscape-automation/tree/master/for-app-developers/cy-automation-taskfactory-sample)

## Producting JSON Output from Tasks

```ReturnJSONTask``` implements ```ObservableTask``` to produce JSON output. To do this, it satisfies two primary requirements:

1. The ```getResults(Class<?>)``` method recognizes the ```JSONResult``` class, and returns a ```JSONResult``` object. 
2. The ```getResultDescriptor()``` method returns a ```ResultDescriptor``` implementation that exposes the classes of objects that the ```getResults(Class<?>)``` method is capable of returning.

### Implementing JSONResult

The ```getResults(Class<?>)``` method in ```ReturnJSONTask``` returns an instance of ```SampleJSONResult```, as shown below:

```java
public <R> R getResults(Class<? extends R> type) {
	...
        if (type.equals(JSONResult.class)) {
		return (R) new SampleJSONResult(result);
	}
        ...
}
```

```SampleJSONResult``` is responsible for transforming the contents of an instance of ```SampleResult``` into JSON.

```SampleResult``` is a very simple Java class, which consists of nothing but public fields.

```java
public class SampleResult {
	public String name;
	public List<Integer> values;
}
```
Classes of this type are very easily translated into JSON by libraries such as GSON or Jackson. An example of the JSON produced by an instance of this class can look like the following:

```json
{
  "data": {
    "results": [
      {
        "name": "Hodor",
        "values": [
          1,
          2,
          3
        ]
      }
    ]
  },
  "errors": []
}
```

```SampleJSONResult``` performs such a translation using the following code:

```java
public String getJSON() {
	Gson gson = new Gson();
	return gson.toJson(result);
}
```

Additional details regarding the implementation of these classes are contained in comments in the sample code.

### Implementing ResultDescriptor

Once a ```JSONResult``` implementation is created, Cytoscape needs a way to recognize that ```ReturnJSONTask``` can provide it as a result. The ```ObservableTask``` interface includes the ```getResultDescriptor()``` method to achieve this. A default implementation is provided in the interface, but this returns ```null```.

```ReturnJSONTask```s implementation of this method returns an instance of ```SampleResultDescriptor```, which lists the result types, including ```JSONResult``` returned through the following code:

```java
public List<Class<?>> getResultTypes() {
	return Collections.unmodifiableList(Arrays.asList(String.class, SampleResult.class, JSONResult.class));
}
```

In addition, ```SampleResultDescriptor``` also provides example outputs for each of the returned Types, which can be used to generate Swagger documentation. Additional details regarding the implementation of these classes are contained in comments in the sample code.

## Accessing JSON through automation

Using the path ```POST /v1/commands/sample_app/return_json``` you can examine the output of ```ReturnJSONTask``` through some of the methods defined in [Accessing Automation](https://github.com/cytoscape/cytoscape-automation/wiki/App-Developers:-Accessing-Automation).

In addition, basic python integration tests are included as sample code in the ```python_tests``` directory.

## Next Steps

More detailed resources regarding Commands and Tasks can be found below:

[Cytoscape Manual: Command Tool](http://manual.cytoscape.org/en/stable/Command_Tool.html)

[org.cytoscape.work JavaDoc](http://code.cytoscape.org/jenkins/job/cytoscape-3-javadoc/javadoc/org/cytoscape/work/package-summary.html)

