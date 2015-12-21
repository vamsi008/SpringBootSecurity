package com.employee.app.vo;

import java.util.List;

import com.employee.app.stringcontants.Status;

public class Result {

	private Object resultObject;
	private List<String> errorList;
	private Status status;

	public Object getResultObject() {
		return resultObject;
	}

	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
