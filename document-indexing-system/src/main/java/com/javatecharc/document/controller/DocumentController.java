package com.javatecharc.document.controller;

import com.javatecharc.document.model.DocumentMetadata;
import com.javatecharc.document.service.ElasticsearchIndexer;
import com.javatecharc.document.service.MetadataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class DocumentController {

    @Autowired
    private MetadataExtractor metadataExtractor;
    @Autowired
    private ElasticsearchIndexer indexer;

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public String uploadDocument(@RequestParam("file") MultipartFile file,
                                 @RequestParam("department") String department,
                                 Model model) {
        try {
            DocumentMetadata metadata = metadataExtractor.extractMetadata(file, department);
            indexer.indexDocument(metadata);
            model.addAttribute("message", "File indexed successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Failed: " + e.getMessage());
        }

        return "result";
    }
}
