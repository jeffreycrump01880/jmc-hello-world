package com.jmc.hello.sqs;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class SQSPublisher {
    
    private static final Logger LOG = LogManager.getLogger(SQSPublisher.class);
    
    @Value("${sqs.queue.url}")
    private String queueUrl;
     
    public void publishMessage(String jsonPayload) {
        
        LOG.warn("Publishing to url=[" + queueUrl + "] payload=" + jsonPayload);
          
        // Per Javadoc, this is the "Default client using the DefaultAWSCredentialsProviderChain and DefaultAwsRegionProviderChain chain"
        // So when running locally, the credentials must be in the default location.
        //
        // NEEDSWORK: That's kinda crappy, since it looks on the local filesystem for credentials. That's OK for now, I suppose.
        // When deployed in AWS, it looks through the environment for the application owner.
        
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
               
        SendMessageRequest msg =  new SendMessageRequest(queueUrl, jsonPayload);
        msg.setMessageGroupId(UUID.randomUUID().toString());
        msg.setMessageDeduplicationId(UUID.randomUUID().toString());
        sqs.sendMessage(msg);

    }
    
}
