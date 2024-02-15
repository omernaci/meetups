package com.omernaci.dockerdemo.service.impl;

import com.omernaci.dockerdemo.domain.Article;
import com.omernaci.dockerdemo.dto.ArticleDto;
import com.omernaci.dockerdemo.repository.ArticleRepository;
import com.omernaci.dockerdemo.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto createArticle(ArticleDto request) {

        ModelMapper modelMapper = new ModelMapper();
        Article article = articleRepository.save(modelMapper.map(request, Article.class));

        return modelMapper.map(article, ArticleDto.class);
    }

    @Override
    public Optional<ArticleDto> getArticle(Long id) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isPresent()) {

            ModelMapper modelMapper = new ModelMapper();

            return Optional.ofNullable(modelMapper.map(article.get(), ArticleDto.class));
        }

        return Optional.empty();
    }

}
