package com.deviget.core.logger;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.util.Date;
import org.apache.logging.log4j.Logger;


public class StepLogger {

	public static Logger logger;
	static Integer id;
	static Integer testCaseId;
	static Long testStart;
	static String stepIdVar = "";
	static String logMessages = "";

	private StepLogger() {
	}

	public static void setCaseId(Integer theCaseId, WebDriver webDriver) {
		testCaseId = theCaseId;
		logger = LogManager.getLogger("C" + theCaseId);
		id = 1;
		logMessages = "";
		testStart = new Date().getTime();
	}

	@Step("Step: {stepName}")
	public static void step(String stepName) {
		String operation = "Pre-Condition";
		if (testCaseId != null) {
			operation = "Step";
		}
		commonLogger(operation, stepName);
	}

	@Step("Step: {stepId}")
	public static void stepId(Integer stepId) {
		id = stepId != null ? stepId : id + 1;
		commonLogger("Step Id", id.toString());
	}

	@Step("Verification: {verificationDescription}")
	public static void verification(String verificationDescription) {
		commonLogger("Verification", verificationDescription);
	}

	@Step("Pre-Condition: {preConditionDescription}")
	public static void preCondition(String preConditionDescription) {
		commonLogger("Pre-Condition", preConditionDescription);
	}

	@Step("{postConditionDescription}")
	public static void postCondition(String postConditionDescription) {
		commonLogger("Post-Condition", postConditionDescription);
	}

	@Step("Sub-step: {stepName}")
	public static void subStep(String stepName) {
		commonLogger("Sub-Step", stepName);
	}

	@Step("Sub-Verification: {verificationDescription}")
	public static void subVerification(String verificationDescription) {
		commonLogger("Sub-Verification", verificationDescription);
	}

	@Step("Sub-step: {stepName}")
	public static void subStep(String stepName, WebElement element) {
		commonLogger("Sub-Step", stepName);
	}

	@Step("Sub-Verification: {verificationDescription}")
	public static void subVerification(String verificationDescription, WebElement element) {
		commonLogger("Sub-Verification", verificationDescription);
	}

	public static void commonLogger(String operation, String step) {
		String message = stepIdVar + "- *" + operation + "* - " + step;
		if (testStart != null) {
			Long passed = new Date().getTime() - testStart;
			message = " +" + passed / 1000 + "s " + message;
		}
		if (logger != null) {
			logger.debug(message);
		} else {
			logMessages += message + "\n";
		}
	}
}
