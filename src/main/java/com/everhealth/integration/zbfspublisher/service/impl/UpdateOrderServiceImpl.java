package com.everhealth.integration.zbfspublisher.service.impl;

import com.everhealth.integration.zbfspublisher.modal.ApplicationConstants;
import com.everhealth.integration.zbfspublisher.modal.UpdateOrderZbRequest;
import com.everhealth.integration.zbfspublisher.modal.UpdateOrderZbResponse;
import com.everhealth.integration.zbfspublisher.service.UpdateOrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

@Service
public class UpdateOrderServiceImpl implements UpdateOrderService {

	Logger LOG = LoggerFactory.getLogger(UpdateOrderServiceImpl.class);
	private final KafkaTemplate<String, Object> kafkaTemplate;
	private final ZuoraAuthServiceImpl zuoraAuthServiceImpl;
	private final FetchSubscriptionServiceImpl fetchSubscriptionServiceImpl;

	@Autowired
	public UpdateOrderServiceImpl(KafkaTemplate<String, Object> kafkaTemplate,
								  ZuoraAuthServiceImpl zuoraAuthServiceImpl,
								  FetchSubscriptionServiceImpl fetchSubscriptionServiceImpl) {
		this.kafkaTemplate = kafkaTemplate;
		this.zuoraAuthServiceImpl = zuoraAuthServiceImpl;
		this.fetchSubscriptionServiceImpl = fetchSubscriptionServiceImpl;
	}

	@Override
	public UpdateOrderZbResponse addProduct(UpdateOrderZbRequest updateOrderZbRequest) {
		
    	LOG.info("UpdateOrderService - Entered addProduct method with request: {}", updateOrderZbRequest);
		
		UpdateOrderZbResponse updateOrderZbResponse;
		try {
			// Fetch details from Zuora billing
			String subscriptionNumber = updateOrderZbRequest.getSubscriptions().getFirst().getSubscriptionNumber();
			String accessToken = zuoraAuthServiceImpl.getOAuthToken();
			LOG.info("Retrieved oauth token is: {}", accessToken);
			String subscriptionDetail = fetchSubscriptionServiceImpl.getSubscriptionDetails(accessToken, subscriptionNumber);
			LOG.info("GET subscriptionDetail Response from Zoura Billing: {}", subscriptionDetail);

			// Publish request to Kafka topic
			UpdateOrderZbRequest addProductPublishRequest = constructAddProductPublishRequest(updateOrderZbRequest);
			kafkaTemplate.send("zb_fs_publisher_topic_local", addProductPublishRequest);
			LOG.info("Published event to Kafka topic successfully...");
		}
		catch(KafkaException ex){
			LOG.error("Kafka exception occurred. Failed to publish event.", ex);
		}
		catch(Exception ex) {
			LOG.error("Unexpected exception occurred", ex);
		}
		updateOrderZbResponse = constructAddProductResponse(updateOrderZbRequest);
		
    	LOG.info("UpdateOrderService - Exiting addProduct method with response: {}", updateOrderZbResponse);
		return updateOrderZbResponse;
		
	}
	
	/**
	 * Method to construct request to publish in Kafka
	 * 
	 * @param updateOrderZbRequest updateOrderZbRequest
	 * @return kafka publish request
	 */
	private UpdateOrderZbRequest constructAddProductPublishRequest(UpdateOrderZbRequest updateOrderZbRequest) throws Exception {
		
		// recursion method to copy pojo to pojo
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(mapper.writeValueAsString(updateOrderZbRequest));
		updateDateFormat(rootNode);
		String publishRequestJsonString = mapper.writeValueAsString(rootNode);
		
		return mapper.readValue(publishRequestJsonString, UpdateOrderZbRequest.class);
	}

	/**
	 * Method to update date fields in JSON
	 * in expected format
	 * 
	 * @param rootNode rootNode
	 */
	private void updateDateFormat(JsonNode rootNode) {
		if (rootNode.isObject()) {
			
            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                JsonNode childNode = entry.getValue();
                if (childNode.isTextual() && isDate(childNode.asText()))
                {
                    ((ObjectNode) rootNode).put(entry.getKey(), convertDateFormat(childNode.asText()));
                }
                else {
                    updateDateFormat(childNode);
                }
            }
        } else if (rootNode.isArray()) {
            for (JsonNode childNode : rootNode) {
                updateDateFormat(childNode);
            }
        }
	}
	
	private static boolean isDate(String requestDate) {
        return requestDate.matches("\\d{4}-\\d{2}-\\d{2}");
    }
	
	/**
	 * Method to convert request date format from yyyy-MM-dd to MM/dd/yyyy
	 * 
	 * @param requestDate requestDate
	 * @return responseDate
	 */
	private String convertDateFormat(String requestDate) {

		LocalDate receivedDate = LocalDate.parse(requestDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String responseDate = receivedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

		LOG.info("Date string: {}", responseDate);
		return responseDate;
	}
	
	/**
	 * Method to prepare API response object
	 * 
	 * @param updateOrderZbRequest request
	 * @return successResponse
	 */
	private UpdateOrderZbResponse constructAddProductResponse(UpdateOrderZbRequest updateOrderZbRequest) {
		
		UpdateOrderZbResponse response = new UpdateOrderZbResponse();
		
		response.setAccountNumber(updateOrderZbRequest.getExistingAccountNumber());
		response.setSuccess(Boolean.TRUE);
		response.setMessage(ApplicationConstants.STATUS_MESSAGE_SUCCESS);
		
		return response;
	}

}
