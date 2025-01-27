package com.example.shamir_secret_sharing.dto;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class DecryptRequest {
    private String documentId;
    private Map<Integer, String> fragments;

    // Getters and Setters
    // documentId
    public String getDocumentId() { return documentId; }
    public void setDocumentId(String documentId) { this.documentId = documentId; }

    // fragments
    public Map<Integer, String> getFragments() { return fragments; }
    public void setFragments(Map<Integer, String> fragments) { this.fragments = fragments; }

    // Convertion des fragments
    public Map<Integer, BigInteger> getFragmentsAsBigInteger() {
        Map<Integer, BigInteger> convertedFragments = new HashMap<>();
        for (Map.Entry<Integer, String> entry : fragments.entrySet()) {
            convertedFragments.put(entry.getKey(), new BigInteger(entry.getValue()));
        }
        return convertedFragments;
    }
}