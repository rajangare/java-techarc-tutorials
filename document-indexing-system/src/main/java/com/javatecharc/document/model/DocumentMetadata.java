package com.javatecharc.document.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DocumentMetadata implements Serializable {
    private String documentId;
    private String documentName;
    private String documentType;
    private String creationDate;
    private String department;
    private List<String> contentTags;
    private String contentText;
    private String uploadTimestamp;
}
