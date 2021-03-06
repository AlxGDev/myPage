package org.alexg.mypage.linkshortener.service;

import java.util.ArrayList;
import java.util.List;

import org.alexg.mypage.exception.InvalidURLException;
import org.alexg.mypage.linkshortener.dto.LinkDTO;
import org.alexg.mypage.linkshortener.entities.LinkEntity;
import org.alexg.mypage.linkshortener.repository.LinkRepository;
import org.alexg.mypage.linkshortener.service.HashService;
import org.alexg.mypage.linkshortener.service.LinkService;
import org.alexg.mypage.linkshortener.service.verifier.UrlVerifiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LinkServiceImpl implements LinkService {
	private static final Logger logger = LoggerFactory.getLogger(LinkServiceImpl.class);
	private static final String URL_ENCODE_REGEX = "[a-zA-Z0-9_~-]+$";
	private LinkRepository linkRepository;
	private HashService shortenService;
	private UrlVerifiers urlVerifiers;

	@Autowired
	public LinkServiceImpl(HashService shortenService, LinkRepository linkRepository, UrlVerifiers urlVerifiers) {
		this.linkRepository = linkRepository;
		this.shortenService = shortenService;
		this.urlVerifiers = urlVerifiers;
	}

	@Override
	@Transactional
	public LinkDTO create(String url) {
		logger.debug("Request to shorten: {}", url);
		LinkEntity resultLink;
		
		//boolean isSafeUrl = true; 
		boolean isSafeUrl = urlVerifiers.isSafe(url);
	    if (isSafeUrl) {
	      LinkEntity existingLink = linkRepository.findByUrl(url);
	      if (existingLink != null) {
	        logger.debug("URL {} already exists in database: {}", url, existingLink);
	        resultLink = existingLink;
	      } else {
	        resultLink = createAndSaveLink(url);
	      }
	      return LinkDTO.mapFromLinkEntity(resultLink);
	    } else {
	      throw new InvalidURLException("URL " + url + " might pose a security risk, so we won't process it");
	    }
		
		
	}

	private LinkEntity createAndSaveLink(String url) {
		String hash = shortenService.shorten(url);
		
		LinkEntity requestedLink = new LinkEntity();
		requestedLink.setUrl(url);
		requestedLink.setHash(hash);
		LinkEntity savedLink = linkRepository.save(requestedLink);
		logger.debug("Successfully created new link: {}", savedLink);
		return savedLink;
	}
	
	@Override
	 @Transactional(readOnly = true)
	public List<LinkDTO> findAll() {
		 List<LinkEntity> foundLinks = linkRepository.findAll();
		 List<LinkDTO> convertedLinks = new ArrayList<LinkDTO>();
		 for(LinkEntity a : foundLinks){
			 convertedLinks.add(LinkDTO.mapFromLinkEntity(a));
		 }

	 	return convertedLinks;
	 }
	
	@Override
	public String findUrlByHash(String hash) {
		String url;

	    logger.trace("Retrieving link for the hash: ", hash);
	    LinkEntity foundLink = linkRepository.findByHash(hash);
	    if (foundLink == null) {
	      logger.info("No link found for hash: {}", hash);
	      url = "http://www.google.com";
	    } else {
	      logger.debug("Found link corresponding to the hash: {} is {}", foundLink);
	      url = foundLink.getUrl();
	    }

	    return url;
	  }
}

