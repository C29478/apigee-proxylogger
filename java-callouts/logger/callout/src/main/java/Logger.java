package com.abnamro.openbankingplatform;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.execution.spi.Execution;
import com.apigee.flow.message.MessageContext;


public class Logger implements Execution {
	
	/**
	 * Stores the variables in a log object
	 * 
	 * The initializer has already executed so this methods first gets the
         * variables that need to be logged (logVariables) and the object containing
         * the variables collected thusfar (log)
	 *
	 * @param messageContext
	 * @param executionResult 
	 * @return ExecutionResult containing status of the execution
         */
	public ExecutionResult execute(MessageContext messageContext, ExecutionContext executionContext) {
		
		try {
			// Gather log variables
			String[] logVariables = this.gatherLogVariables(messageContext);
			
			// Get the log
			JSONArray log = this.getLog(messageContext); 
			
			// Create object for containing log values
			JSONObject event = createEventObject(messageContext, logVariables);

			// Add the event to the log
			log.add(event);
			
			// Set the context variable equal to the log.
			messageContext.setVariable("events", log);

            		return ExecutionResult.SUCCESS;

		} catch (Exception e) {
			return ExecutionResult.ABORT;
		}
	}

	/** 
	 * Retrieves variables that need to be logged from the messageContext
	 *
	 * These variables are retrieved by GetLogVariables policy from environment KVM
	 *
	 * @return An Array of Strings containing names of variables to be logged
	 */
	private static String[] gatherLogVariables(MessageContext messageContext) {

		String logVariablesFromContext = messageContext.getVariable("logVariables");
		
		String[] logVariables = logVariablesFromContext.split(",");

		return logVariables;
	}

	/** 
	 * Retrieves the variables that were gather thusfar by the proxy
	 * 
	 * @return JSONArray containing the variables stored thusfar
	 */
	private static JSONArray getLog(MessageContext messageContext) {

		JSONArray log = messageContext.getVariable("events");

		return log;

	}


	private static JSONObject createEventObject(MessageContext messageContext, String[] variables) {
		JSONObject event = new JSONObject();

			for (int i = 0; i < variables.length; i++) {

				String key = variables[i];

				Object value = messageContext.getVariable(key);

				boolean isStringOrInt = (value instanceof Integer || value instanceof String);

				if(!isStringOrInt) {
					// Stringify the value and set it as the current value.
					value = String.valueOf(value);
				}

				// Add variable to object.
				event.put(key, value);
			}
			return event;
	}

}
