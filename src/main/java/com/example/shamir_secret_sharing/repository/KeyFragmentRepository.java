package com.example.shamir_secret_sharing.repository;

import com.example.shamir_secret_sharing.entity.KeyFragment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyFragmentRepository extends JpaRepository<KeyFragment, Long> {
    List<KeyFragment> findByDocumentId(String documentId);
}