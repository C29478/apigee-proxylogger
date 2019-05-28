# apigee proxy logger
Log data from an Apigee proxy to a logmanagement tool such as Splunk, Loggly or Elasticsearch

# java callouts
This repo consists of three java callouts that will generate variables in your flow context that 
can be submitted to your log management tool via for example a flow callout policy. 

This code relies on a variable 'logVariables' that contains the flow variables that need to be 
logged. This is a simple string containing the variables seperated by a comma (e.g. "flow.name, 
...). This variable can be hardcoded here but ideally it is populated using some other policy in
your proxy or shared flow like a KVM.

The java callout policies can be exucuted as part of a proxy or a shared flow but they need to be executed in a specific order: 

init -> logger -> submitter

The logger can run as many time as desired.  

## init
Initializes an array called 'events' that will be used by the logger to append the values of the flow variables at the time of the execution of the logger  

## logger
Reads the variable 'logVariables' that contains all flow variables that need to be logged and stores its value in the 'events' array.

## submitter
Prepares the payload needed for submitting the logged values to the logmanagement tool. Currently only Splunk is supported. Adding support for other log management tools can be done here. Submitting the data can be done using a callout policy.

# maven
three folder each containing a maven project that will generate a jar file
that needs to be included in a java callout policy. 

The init, logger and submitter have their own pom.xml file. Before running the maven projects 
ensure that the following apigee libraries are in your local maven repo:

expressions-1.0.0.jar
message-flow-1.0.0.jar

There is a build script included that will do this for you. This script was taken from the offical apigee github repo:

https://github.com/apigee/api-platform-samples

For more information on java callouts in apigee:

https://docs.apigee.com/api-platform/samples/cookbook/how-create-java-callout
