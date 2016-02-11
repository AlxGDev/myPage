package org.alexg.mypage.linkshortener.service;

import org.alexg.mypage.exception.InvalidURLException;
import org.alexg.mypage.linkshortener.service.HashService;
import org.alexg.mypage.linkshortener.service.SupportedProtocol;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HashServiceImpl implements HashService {
	 private static final int RADIX = 36;
	 private static final String PIPE = "-";

	@Override
	 public String shorten(String url) {
		return encode(url);
	 }

	private String encode(String url) {
	    if (StringUtils.isEmpty(url)) {
	      throw new InvalidURLException("Supplied invalid url: empty");
	    }

	    boolean isSupportedProtocol = SupportedProtocol.contains(url);
	    if (!isSupportedProtocol) {
	      throw new InvalidURLException("URL protocol not supported");
	    }

	    String hexValue = Integer.toString(url.hashCode(), RADIX);
	    if (hexValue.startsWith(PIPE)) {
	      hexValue = hexValue.substring(1);
	    }

	    // TODO: Implement database check to prevent collisions
	    return hexValue;
	 }
}
