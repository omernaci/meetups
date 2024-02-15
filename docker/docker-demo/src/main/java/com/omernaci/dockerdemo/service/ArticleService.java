package com.omernaci.dockerdemo.service;

import com.omernaci.dockerdemo.dto.ArticleDto;

import java.util.Optional;

public interface ArticleService {

    ArticleDto createArticle(ArticleDto request);

    Optional<ArticleDto> getArticle(Long id);

}
