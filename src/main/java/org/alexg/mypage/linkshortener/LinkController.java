package org.alexg.mypage.linkshortener;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.alexg.mypage.linkshortener.dto.LinkDTO;
import org.alexg.mypage.linkshortener.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LinkController.LINKS)
public class LinkController {
	public static final String LINKS = "/api/links";
	private LinkService linkService;

	@Autowired
	public LinkController(LinkService linkService) {
		this.linkService = linkService;
	}
	
	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<LinkDTO>> findAll() {
		 List<LinkDTO> links = linkService.findAll();
	
		return ResponseEntity.ok().body(links);
	 }

	
	


	@RequestMapping(method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<LinkDTO> createShortLink(@RequestParam(value = "url") String longUrl) {
		LinkDTO savedLink = linkService.create(longUrl);
	
		return ResponseEntity.ok().body(savedLink);
	}
}
