package org.alexg.mypage.linkshortener.dto;

import org.alexg.mypage.linkshortener.entities.LinkEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LinkDTO {
	@JsonIgnore
	private long id;
	private String hash;
	private String url;
	
	public LinkDTO(long id, String hash, String url){
		this.id=id;
		this.hash=hash;
		this.url=url;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public static LinkDTO mapFromLinkEntity(LinkEntity link) {
        return new LinkDTO(link.getId(),link.getHash(),link.getUrl());
    }
	

}
