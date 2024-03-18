package br.com.postech.sevenfood.commons.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CognitoConfig {

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Bean
    public AWSCognitoIdentityProvider amazonCognitoIdentityProvider() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1) // Defina a região correta
                .build();
    }
}
