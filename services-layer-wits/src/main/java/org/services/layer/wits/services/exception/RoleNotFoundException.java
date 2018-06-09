package org.services.layer.wits.services.exception;

public class RoleNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RoleNotFoundException ()
	{
	}

	public RoleNotFoundException (String message)
	{
		super (message);
	}

	public RoleNotFoundException (Throwable cause)
	{
		super (cause);
	}

	public RoleNotFoundException (String message, Throwable cause)
	{
		super (message, cause);
	}

}
