package com.omernaci.dockerdemo.controller;

import com.omernaci.dockerdemo.dto.ArticleDto;
import com.omernaci.dockerdemo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/api/v1/articles"}, produces = APPLICATION_JSON_VALUE)
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> createArticle(@RequestBody ArticleDto request) {
        final ArticleDto createdOrder = articleService.createArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable(value = "id") Long id) {
        final Optional<ArticleDto> article = articleService.getArticle(id);

        if (article.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article.get());
    }
}
