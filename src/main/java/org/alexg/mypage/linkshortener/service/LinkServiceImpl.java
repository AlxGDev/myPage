package org.alexg.mypage.linkshortener.service;

import org.alexg.mypage.linkshortener.dto.LinkDTO;
import org.alexg.mypage.linkshortener.entities.LinkEntity;
import org.alexg.mypage.linkshortener.repository.LinkRepository;
import org.alexg.mypage.linkshortener.service.HashService;
import org.alexg.mypage.linkshortener.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LinkServiceImpl implements LinkService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkServiceImpl.class);
	private LinkRepository linkRepository;
	private HashService shortenService;

	@Autowired
	public LinkServiceImpl(HashService shortenService, LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
		this.shortenService = shortenService;
	}

	@Override
	@Transactional
	public LinkDTO create(String url) {
		LinkEntity resultLink;
		
		LinkEntity existingLink = linkRepository.findByUrl(url);
		if (existingLink != null) {
			resultLink = existingLink;
		} else {
			resultLink = createAndSaveLink(url);
		}
		
		return LinkDTO.mapFromLinkEntity(resultLink);
	}

	private LinkEntity createAndSaveLink(String url) {
		String hash = shortenService.shorten(url);
		
		LinkEntity requestedLink = new LinkEntity();
		requestedLink.setUrl(url);
		requestedLink.setHash(hash);
		LinkEntity savedLink = linkRepository.save(requestedLink);
		
		return savedLink;
	}
	
	@Override
	public String findUrlByHash(String hash) {
	    String url = null;

	    LOGGER.trace("Retrieving link for the hash: ", hash);
	    LinkEntity foundLink = linkRepository.findByHash(hash);
	    if (foundLink == null) {
	      LOGGER.info("No link found for hash: {}", hash);
	    } else {
	      LOGGER.debug("Found link corresponding to the hash: {} is {}", foundLink);
	      url = foundLink.getUrl();
	    }

	    return url;
	  }
}

