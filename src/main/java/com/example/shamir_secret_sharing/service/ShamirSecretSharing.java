package com.example.shamir_secret_sharing.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe implémentant l'algorithme de Shamir's Secret Sharing.
 * Permet de diviser un secret en plusieurs fragments (shares)
 * et de reconstruire le secret à partir d'un sous-ensemble de ces fragments.
 */
public class ShamirSecretSharing {

    /**
     * Divise un secret en plusieurs fragments.
     *
     * @param secret Le secret à diviser (représenté par un BigInteger).
     * @param n Le nombre total de fragments à générer.
     * @param t Le nombre minimal de fragments nécessaires pour reconstruire le secret.
     * @return Une map contenant les fragments générés, où la clé est l'indice du fragment et la valeur est le fragment lui-même.
     */
    public static Map<Integer, BigInteger> splitSecret(BigInteger secret, int n, int t) {
        SecureRandom random = new SecureRandom();
        BigInteger[] coefficients = new BigInteger[t - 1];

        for (int i = 0; i < t - 1; i++) {
            coefficients[i] = new BigInteger(secret.bitLength(), random);
        }

        Map<Integer, BigInteger> shares = new HashMap<>();
        for (int x = 1; x <= n; x++) {
            BigInteger y = secret;
            for (int i = 0; i < t - 1; i++) {
                y = y.add(coefficients[i].multiply(BigInteger.valueOf((long) Math.pow(x, i + 1))));
            }
            shares.put(x, y);
        }
        return shares;
    }

    /**
     * Reconstruit le secret à partir d'au moins t fragments.
     *
     * @param shares Une map contenant les fragments utilisés pour la reconstruction (clé = indice, valeur = fragment).
     * @return Le secret reconstruit sous forme de BigInteger.
     */
    public static BigInteger reconstructSecret(Map<Integer, BigInteger> shares) {
        BigInteger secret = BigInteger.ZERO;

        for (Map.Entry<Integer, BigInteger> entry : shares.entrySet()) {
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (Map.Entry<Integer, BigInteger> entry2 : shares.entrySet()) {
                if (!entry.getKey().equals(entry2.getKey())) {
                    numerator = numerator.multiply(BigInteger.valueOf(-entry2.getKey()));
                    denominator = denominator.multiply(BigInteger.valueOf(entry.getKey() - entry2.getKey()));
                }
            }
            secret = secret.add(entry.getValue().multiply(numerator).divide(denominator));
        }
        return secret;
    }
}