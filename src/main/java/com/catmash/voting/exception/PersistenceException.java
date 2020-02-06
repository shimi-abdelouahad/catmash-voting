package com.catmash.voting.exception;

public class PersistenceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1102772305212590291L;

	public PersistenceException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
