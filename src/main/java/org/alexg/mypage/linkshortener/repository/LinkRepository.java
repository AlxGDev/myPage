package org.alexg.mypage.linkshortener.repository;


import org.alexg.mypage.linkshortener.entities.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<LinkEntity, Long> {

  LinkEntity findByHash(String hash);

  LinkEntity findByUrl(String url);

}
