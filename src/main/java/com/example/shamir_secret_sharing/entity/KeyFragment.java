package com.example.shamir_secret_sharing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KeyFragment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentId;
    private int fragmentIndex;
    private String fragmentValue;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDocumentId() { return documentId; }
    public void setDocumentId(String documentId) { this.documentId = documentId; }

    public int getFragmentIndex() { return fragmentIndex; }
    public void setFragmentIndex(int fragmentIndex) { this.fragmentIndex = fragmentIndex; }

    public String getFragmentValue() { return fragmentValue; }
    public void setFragmentValue(String fragmentValue) { this.fragmentValue = fragmentValue; }
}
