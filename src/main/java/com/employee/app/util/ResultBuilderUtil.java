package com.employee.app.util;

import java.util.List;

import com.employee.app.stringcontants.Status;
import com.employee.app.vo.Result;

public class ResultBuilderUtil {

	public static Result buildResult(Status create, List<String> errorMessageList, Object data) {
		Result result = new Result();
		result.setStatus(create);
		result.setResultObject(data);
		if(errorMessageList!=null){
			result.setErrorList(errorMessageList);
		}
		return result;
	}

}
