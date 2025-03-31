package com.ts_voc_back.common.model;

import java.util.List;

public class ComResult<T> {
	private String resultCode;
    /* 결과메세지 */
    private String resultMessage;
    /* 결과데이터 */
    private T resultData;
    /* 결과데이터(배열) */
    private List<T> resultList;
    /* 파라미터 */
    private Object param;
    /* 예외처리(오류) */
    private Exception exception;
    
    public ComResult() {
	}
    
    public ComResult(Object param) {
        this.param = param;
    }

	public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setSuccess(T resultData) {
        this.resultData = resultData;
        this.resultCode = "SUCCESS";
        this.resultMessage = "";
    }

    public void setSuccess(T resultData, String resultMessage) {
        this.resultData = resultData;
        this.resultCode = "SUCCESS";
        this.resultMessage = resultMessage;
    }

    public void setSuccess(List<T> resultList) {
        if (!resultList.isEmpty()) {
            this.resultData = resultList.get(0);
        }

        this.resultList = resultList;
        this.resultCode = "SUCCESS";
        this.resultMessage = "";
    }

    public void setSuccess(List<T> resultList, String resultMessage) {
        if (!resultList.isEmpty()) {
            this.resultData = resultList.get(0);
        }

        this.resultList = resultList;
        this.resultCode = "SUCCESS";
        this.resultMessage = resultMessage;
    }

    public void setFail(String resultMessage) {
        this.resultCode = "FAIL";
        this.resultMessage = resultMessage;
    }

    public void setError(Exception exception) {
        this.resultCode = "ERROR";
        this.resultMessage = exception.toString();
        this.exception = exception;

        System.out.println( exception.getMessage() );
        System.out.println( exception.getStackTrace() );
        exception.printStackTrace();
    }

    public void setError(Exception exception, String resultMessage) {
        this.resultCode = "ERROR";
        this.resultMessage = resultMessage;
        this.exception = exception;
    }
}
