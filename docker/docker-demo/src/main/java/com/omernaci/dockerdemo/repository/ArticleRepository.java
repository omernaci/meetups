package com.omernaci.dockerdemo.repository;

import com.omernaci.dockerdemo.domain.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
