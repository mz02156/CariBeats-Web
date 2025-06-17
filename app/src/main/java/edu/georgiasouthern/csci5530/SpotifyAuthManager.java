package edu.georgiasouthern.csci5530;

import android.util.Base64;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class SpotifyAuthManager {
    private static final String CLIENT_ID = "9913d28864dd45459a710431e3075c26";
    private static final String REDIRECT_URI = "https://mz02156.github.io/CariBeats-Web/";
    private static final String AUTHORIZATION_ENDPOINT = "https://accounts.spotify.com/authorize";
    private static final String TOKEN_ENDPOINT = "https://accounts.spotify.com/api/token";

    private static String codeVerifier;

    public static String generateCodeVerifier() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] code = new byte[32]; // 32 bytes = 43 characters when Base64 encoded (safe length)
        secureRandom.nextBytes(code);
        codeVerifier = base64UrlEncode(code);
        return codeVerifier;
    }

    public static String generateCodeChallenge(String verifier) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(verifier.getBytes(StandardCharsets.US_ASCII));
        return base64UrlEncode(hash);
    }

    private static String base64UrlEncode(byte[] input) {
        return Base64.encodeToString(input, Base64.URL_SAFE | Base64.NO_PADDING | Base64.NO_WRAP);
    }

    public static String buildAuthorizationUrl() throws Exception {
        String verifier = generateCodeVerifier();
        String challenge = generateCodeChallenge(verifier);

        return AUTHORIZATION_ENDPOINT +
                "?client_id=" + CLIENT_ID +
                "&response_type=code" +
                "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") +
                "&code_challenge_method=S256" +
                "&code_challenge=" + challenge +
                "&scope=user-read-private user-read-email";
    }

    public static String getCodeVerifier() {
        return codeVerifier;
    }

    public static String getTokenEndpoint() {
        return TOKEN_ENDPOINT;
    }

    public static String getRedirectUri() {
        return REDIRECT_URI;
    }

    public static String getClientId() {
        return CLIENT_ID;
    }
}

