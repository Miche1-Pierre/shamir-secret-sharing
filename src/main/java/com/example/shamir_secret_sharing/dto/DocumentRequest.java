package com.example.shamir_secret_sharing.dto;

public class DocumentRequest {
    private String documentId;
    private String content;
    private int n;
    private int t;

    // Getters and Setters
    // documentId
    public String getDocumentId() { return documentId; }
    public void setDocumentId(String documentId) { this.documentId = documentId; }

    // content
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    // n
    public int getN() { return n; }
    public void setN(int n) { this.n = n; }

    // t
    public int getT() { return t; }
    public void setT(int t) { this.t = t; }
}