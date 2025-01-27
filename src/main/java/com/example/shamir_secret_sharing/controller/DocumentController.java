package com.example.shamir_secret_sharing.controller;

import com.example.shamir_secret_sharing.dto.DecryptRequest;
import com.example.shamir_secret_sharing.dto.DocumentRequest;
import com.example.shamir_secret_sharing.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private KeyService keyService;

    /**
     * Endpoint pour chiffrer un document et stocker les fragments dans la base.
     *
     * @param request Requête contenant le contenu, n, t et l'ID du document.
     * @return Message de confirmation.
     */
    @PostMapping("/encrypt")
    public String encryptDocument(@RequestBody DocumentRequest request) {
        Map<Integer, BigInteger> fragments = keyService.generateAndSaveKeyFragments(
                request.getContent(),
                request.getN(),
                request.getT(),
                request.getDocumentId()
        );
        return "Document encrypté et fragments enregistrés.";
    }

    /**
     * Endpoint pour déchiffrer un document en utilisant les fragments de la base et un fragment admin.
     *
     * @param request Requête contenant l'ID du document et le fragment admin.
     * @return Clé reconstituée ou message d'erreur.
     */
    @PostMapping("/decrypt")
    public String decryptDocument(@RequestBody DecryptRequest request) {
        // Récupérer les fragments depuis la base
        Map<Integer, BigInteger> fragments = keyService.getFragmentsFromDatabase(request.getDocumentId());

        // Ajouter le fragment admin au Map
        fragments.putAll(request.getFragmentsAsBigInteger());

        // Reconstituer la clé
        String key = keyService.reconstructKey(fragments);
        return "Document déchiffré avec la clé : " + key;
    }
}
