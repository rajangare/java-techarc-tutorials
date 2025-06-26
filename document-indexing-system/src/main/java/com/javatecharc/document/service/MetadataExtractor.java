package com.javatecharc.document.service;

import com.javatecharc.document.model.DocumentMetadata;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MetadataExtractor {
    public DocumentMetadata extractMetadata(MultipartFile file, String department) throws IOException, TikaException {
        Tika tika = new Tika();
        String content = tika.parseToString(file.getInputStream());

        DocumentMetadata metadata = new DocumentMetadata();
        metadata.setDocumentId(UUID.randomUUID().toString());
        metadata.setDocumentName(file.getOriginalFilename());
        metadata.setDocumentType(file.getContentType());
        metadata.setCreationDate(LocalDate.now().toString());
        metadata.setDepartment(department);
        metadata.setContentTags(extractTags(content));
        metadata.setContentText(content);
        metadata.setUploadTimestamp(Instant.now().toString());

        return metadata;
    }

    private List<String> extractTags(String content) {
        // Basic keyword tagging using TF or NLP libraries
        return Arrays.stream(content.split("\\s+"))
                .limit(10)
                .collect(Collectors.toList());
    }
}
