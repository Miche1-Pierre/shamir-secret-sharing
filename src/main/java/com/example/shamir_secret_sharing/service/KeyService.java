package com.example.shamir_secret_sharing.service;

import com.example.shamir_secret_sharing.entity.KeyFragment;
import com.example.shamir_secret_sharing.repository.KeyFragmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeyService {

    @Autowired
    private KeyFragmentRepository keyFragmentRepository;

    /**
     * Génère les fragments de clé et les stocke dans la base de données.
     *
     * @param key        La clé secrète à partager.
     * @param n          Nombre total de fragments.
     * @param t          Nombre minimum de fragments nécessaires pour reconstruire la clé.
     * @param documentId Identifiant du document associé.
     * @return Map des fragments générés.
     */
    public Map<Integer, BigInteger> generateAndSaveKeyFragments(String key, int n, int t, String documentId) {
        BigInteger secret = new BigInteger(key.getBytes());
        Map<Integer, BigInteger> fragments = ShamirSecretSharing.splitSecret(secret, n, t);

        // Sauvegarder les fragments dans la base de données
        fragments.forEach((index, fragment) -> {
            KeyFragment keyFragment = new KeyFragment();
            keyFragment.setDocumentId(documentId);
            keyFragment.setFragmentIndex(index); // Ajoutez un champ fragmentIndex si nécessaire
            keyFragment.setFragmentValue(fragment.toString());
            keyFragmentRepository.save(keyFragment);
        });

        return fragments;
    }

    /**
     * Reconstruit la clé à partir des fragments fournis.
     *
     * @param fragments Map des fragments récupérés.
     * @return La clé reconstituée sous forme de chaîne.
     */
    public String reconstructKey(Map<Integer, BigInteger> fragments) {
        BigInteger secret = ShamirSecretSharing.reconstructSecret(fragments);
        return new String(secret.toByteArray());
    }

    /**
     * Récupère les fragments de la base de données associés à un document.
     *
     * @param documentId Identifiant du document.
     * @return Map des fragments (Index -> BigInteger).
     */
    public Map<Integer, BigInteger> getFragmentsFromDatabase(String documentId) {
        List<KeyFragment> fragments = keyFragmentRepository.findByDocumentId(documentId);

        Map<Integer, BigInteger> result = new HashMap<>();
        for (KeyFragment fragment : fragments) {
            result.put(fragment.getFragmentIndex(), new BigInteger(fragment.getFragmentValue()));
        }

        return result;
    }
}
