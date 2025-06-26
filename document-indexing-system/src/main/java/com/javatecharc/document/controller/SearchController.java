package com.javatecharc.document.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.javatecharc.document.model.DocumentMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {
    @Autowired
    private ElasticsearchClient client;

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) throws IOException {
        var result = client.search(s -> s
                .index("documents-index")
                .query(q -> q
                        .multiMatch(m -> m
                                .fields("contentText", "contentTags", "documentName")
                                .query(query)
                        )
                ), DocumentMetadata.class);

        List<DocumentMetadata> docs = result.hits().hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());

        model.addAttribute("results", docs);
        return "searchResults";
    }
}