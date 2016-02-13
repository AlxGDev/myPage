package org.alexg.mypage.linkshortener.service.verifier;

public interface UrlVerifier {

	  public boolean isSafe(String url);

	  public boolean canValidate();

	}
