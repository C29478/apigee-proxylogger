package com.abnamro.openbankingplatform;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.execution.spi.Execution;
import com.apigee.flow.message.MessageContext;


public class Prepper implements Execution {

    private static final String CONTEXT_TARGET_LOG = "events";
    private static final String CONTEXT_TARGET_SUBMITTER = "eventJson";

	/**
	 * Converts the stored variables in a format that is needed by the log management solution of choice
	 *
     * Currently this method assumes that the log management solution is Splunk
     *
	 * @param messageContext
	 * @param executionResult
	 * @return ExecutionResult containing status of the execution
         */
	public ExecutionResult execute(MessageContext messageContext, ExecutionContext executionContext) {

		try {
			JSONArray events = messageContext.getVariable(CONTEXT_TARGET_LOG);
			JSONObject wrapperObject = new JSONObject();
			wrapperObject.put(CONTEXT_TARGET_LOG, events);

			String eventJson = wrapperObject.toJSONString();


			messageContext.setVariable(CONTEXT_TARGET_SUBMITTER, eventJson);

            		return ExecutionResult.SUCCESS;

		} catch (Exception e) {
			return ExecutionResult.ABORT;
		}
	}
}
