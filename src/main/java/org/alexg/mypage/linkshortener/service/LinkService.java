package org.alexg.mypage.linkshortener.service;


import java.util.List;

import org.alexg.mypage.linkshortener.dto.LinkDTO;
import org.alexg.mypage.linkshortener.entities.LinkEntity;

public interface LinkService {
  //List<LinkEntity> find(int offset, int limit);

  LinkDTO create(String url);

  String findUrlByHash(String hash);

  /*void deleteByHash(String hash); */
}
