package com.benkitoucoders.ecommerce.utils;

public class HillCipher {
    private static int[][] key=Constants.key;
    public static String HillCipherCrypt(String plainText) {

        if (plainText.length() % 2 != 0) {
            plainText+='x';
        }

        plainText = plainText.toUpperCase();
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i += 2) {
            String pair = plainText.substring(i, i + 2);
            int[] plainVector = new int[2];

            for (int j = 0; j < 2; j++) {
                plainVector[j] = pair.charAt(j) - 'A';
            }

            int[] encryptedVector = new int[2];

            for (int j = 0; j < 2; j++) {
                encryptedVector[j] = 0;
                for (int k = 0; k < 2; k++) {
                    encryptedVector[j] += key[j][k] * plainVector[k];
                }
                encryptedVector[j] %= 26;
            }

            for (int num : encryptedVector) {
                encryptedText.append((char) (num + 'A'));
            }
        }

        return encryptedText.toString();
    }

    public static int inverseModulo(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1; // No modular inverse found
    }

    public static String HillCipherDecrypt(String ciphertext) {
            int[][] keyMatrix = key;
            int determinant = keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0];
            determinant = (determinant % 26 + 26) % 26; // Ensure a positive modulus value
            int moduloInverse = inverseModulo(determinant, 26);

            if (moduloInverse == -1) {
                throw new IllegalArgumentException("The encryption matrix is not invertible (determinant is zero modulo 26).");
            }

            int[][] keyMatrixInverse = new int[2][2];
            keyMatrixInverse[0][0] = (keyMatrix[1][1] * moduloInverse) % 26;
            keyMatrixInverse[0][1] = (-keyMatrix[0][1] * moduloInverse) % 26;
            keyMatrixInverse[1][0] = (-keyMatrix[1][0] * moduloInverse) % 26;
            keyMatrixInverse[1][1] = (keyMatrix[0][0] * moduloInverse) % 26;

            ciphertext = ciphertext.toUpperCase();
            StringBuilder decryptedText = new StringBuilder();

            for (int i = 0; i < ciphertext.length(); i += 2) {
                int[] block = new int[2];

                for (int j = 0; j < 2; j++) {
                    block[j] = ciphertext.charAt(i + j) - 'A';
                }

                int[] decryptedBlock = new int[2];

                for (int j = 0; j < 2; j++) {
                    decryptedBlock[j] = 0;
                    for (int k = 0; k < 2; k++) {
                        decryptedBlock[j] += keyMatrixInverse[j][k] * block[k];
                    }
                    decryptedBlock[j] = (decryptedBlock[j] % 26 + 26) % 26; // Ensure a positive modulus value
                }

                for (int num : decryptedBlock) {
                    decryptedText.append((char) (num + 'A'));
                }
            }

            return decryptedText.toString();
        }

}
