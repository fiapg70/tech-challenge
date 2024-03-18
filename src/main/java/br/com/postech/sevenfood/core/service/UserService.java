package br.com.postech.sevenfood.core.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    @Value("${cognito.user-pool-id}")
    private String userPoolId;

    @Value("${spring.security.oauth2.client.registration.cognito.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.cognito.client-secret}")
    private String clientSecret;

    @Autowired
    private AWSCognitoIdentityProvider cognitoIdentityProvider;

    public String createUser(String username, String password) {
        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                .withUserPoolId(userPoolId)
                .withUsername(username)
                .withTemporaryPassword(password)
                .withUserAttributes(
                        new AttributeType().withName("email").withValue(username),
                        new AttributeType().withName("email_verified").withValue("true"));

        AdminCreateUserResult createUserResult = cognitoIdentityProvider.adminCreateUser(createUserRequest);
        log.info("User created successfully: " + createUserResult.getUser());
        return createUserResult.getUser().getUsername();
    }

    public String authenticate(String username, String password) {
        String secretHash = calculateSecretHash(clientId, clientSecret, username);

        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withUserPoolId(userPoolId)
                .withClientId(clientId)
                .withAuthFlow("USER_PASSWORD_AUTH")
                .withAuthParameters(
                        Map.of(
                                "USERNAME", username,
                                "PASSWORD", password,
                                "SECRET_HASH", secretHash
                        )
                );

        try {
            AdminInitiateAuthResult authResult = cognitoIdentityProvider.adminInitiateAuth(authRequest);
            AuthenticationResultType authResultType = authResult.getAuthenticationResult();
            return authResultType.getAccessToken();
        } catch (NotAuthorizedException e) {
            log.error("Authentication failed: {}", e.getMessage());
            throw new RuntimeException("Authentication failed", e);
        }
    }

    private String calculateSecretHash(String clientId, String clientSecret, String username) {
        String secretHashSource = clientId + username + clientSecret;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(secretHashSource.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            log.error("Error calculating secret hash: {}", e.getMessage());
            throw new RuntimeException("Failed to calculate secret hash", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}