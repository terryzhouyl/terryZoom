package com.terry;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String ErrorMessage;
	private Integer status;
		
	
	
	public BusinessException(String errorMessage) {
		super();
		ErrorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return ErrorMessage;
	}	
	
}
