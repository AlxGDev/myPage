package org.alexg.mypage.exception;

public class InvalidURLException extends LinkShortenerException{
	 public InvalidURLException(String message) {
		    super(message);
	 }

	 public InvalidURLException(String message, Throwable cause) {
		    super(message, cause);
	 }
}
