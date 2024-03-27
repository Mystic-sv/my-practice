package com.techymeet.mytodo.securityconfig;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        try {
            // Create a SecureRandom instance
            SecureRandom secureRandom = new SecureRandom();

            // Generate a random 512-bit (64-byte) key
            byte[] keyBytes = new byte[64];
            secureRandom.nextBytes(keyBytes);

            // Encode the key to a Base64 string for storage or use
            String base64Key = Base64.getEncoder().encodeToString(keyBytes);

            // Print the generated key
            System.out.println("Generated HS512 Key (Base64):");
            System.out.println(base64Key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
