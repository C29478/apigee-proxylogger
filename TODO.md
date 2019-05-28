# Todo's

- include apiproxy and/or shared flow that contains the java callout policy + jar file
- include deploy script for both apiproxy and shared flow
- include invoke script
- code: use constants throughout; e.g. a constant that specifies the name of the variable containing the flowvariables that need to be logged. Since these constants are used throughout the three classes we should create seperate class containing them?
- code: in submitter create seperate method for submitting to various backends so we can call events.submit(LOG_MANAGEMENT_TOOL) where LOG_MANAGEMENT_TOOL is a constant that can have e.g. value "Splunk" or "Loggly". This method then excutes code as needed for the particular backend
- 
