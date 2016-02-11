package org.alexg.mypage.exception;

public class LinkShortenerException extends RuntimeException {

	  public LinkShortenerException(String message) {
	    super(message);
	  }

	  public LinkShortenerException(String message, Throwable cause) {
	    super(message, cause);
	  }

	}
