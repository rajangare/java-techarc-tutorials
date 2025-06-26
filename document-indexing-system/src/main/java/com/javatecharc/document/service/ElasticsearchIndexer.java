package com.javatecharc.document.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.javatecharc.document.model.DocumentMetadata;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticsearchIndexer {
    private final ElasticsearchClient client;

    public ElasticsearchIndexer(ElasticsearchClient client) {
        this.client = client;
    }

    public void indexDocument(DocumentMetadata metadata) throws IOException {
        IndexRequest<DocumentMetadata> request = IndexRequest.of(i -> i
                .index("documents")
                .id(metadata.getDocumentId())
                .document(metadata));

        client.index(request);
    }
}
