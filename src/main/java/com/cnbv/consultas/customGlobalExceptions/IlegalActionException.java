package com.cnbv.consultas.customGlobalExceptions;

public class IlegalActionException extends IllegalStateException  {

	private static final long serialVersionUID = 1L;
	
	public IlegalActionException(String message) {
		
		super(message);
		
	}
	
	
}
