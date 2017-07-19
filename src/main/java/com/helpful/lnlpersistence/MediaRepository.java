package com.helpful.lnlpersistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface MediaRepository extends JpaRepository<Media, String> {

    List<Media> findByCreateDateBetween(Instant begin, Instant end);
}
