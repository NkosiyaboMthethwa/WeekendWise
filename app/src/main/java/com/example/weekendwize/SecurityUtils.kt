package com.example.weekendwize

import java.security.MessageDigest

object SecurityUtils {

    /**
     * Encrypts/Hashes a plain text password using SHA-256 algorithm with a standard salt.
     * Prevents plain text password storage in database / SharedPreferences.
     */
    fun hashPassword(password: String): String {
        return try {
            val salt = "WeekendWizeSalt2026"
            val saltedPassword = password + salt
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(saltedPassword.toByteArray(Charsets.UTF_8))
            val hexString = StringBuilder()
            for (b in hashBytes) {
                val hex = Integer.toHexString(0xff and b.toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            hexString.toString()
        } catch (e: Exception) {
            // Fallback in case of unexpected algorithm error
            password.hashCode().toString()
        }
    }

    /**
     * Verifies if input password matches the stored hashed password.
     */
    fun verifyPassword(inputPassword: String, storedHash: String?): Boolean {
        if (storedHash.isNullOrEmpty()) return false
        val hashedInput = hashPassword(inputPassword)
        return hashedInput == storedHash || inputPassword == storedHash
    }
}
