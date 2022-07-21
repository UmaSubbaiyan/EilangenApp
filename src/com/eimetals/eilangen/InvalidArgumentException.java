package com.eimetals.eilangen;

import java.util.List;

public class InvalidArgumentException extends RuntimeException{
	
	public InvalidArgumentException(String message, String[] args) {
		super(message + args);
	}
	
	public InvalidArgumentException(String message, String args) {
		//super(message + args);
		super(message + args);
	}
	
	public InvalidArgumentException(String message, List<Integer> args) {
		super(message + args);
	}

}
