package com.abnamro.openbankingplatform;

import org.json.simple.JSONArray;

import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.execution.spi.Execution;
import com.apigee.flow.message.MessageContext;


public class Init implements Execution {
	
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
			JSONArray log = new JSONArray();
			//String[] log = {"something","anotherthing"};

			messageContext.setVariable("events", log);

            		return ExecutionResult.SUCCESS;

		} catch (Exception e) {
			return ExecutionResult.ABORT;
		}
	}
}
