package com.jonevu.abi.security.validation;

public class EmailExistsException extends Throwable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -94310059084413501L;

	public EmailExistsException(final String message) {
        super(message);
    }
}
