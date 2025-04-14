package com.everhealth.integration.zbfspublisher.service;

import com.everhealth.integration.zbfspublisher.modal.UpdateOrderZbRequest;
import com.everhealth.integration.zbfspublisher.modal.UpdateOrderZbResponse;

public interface UpdateOrderService {
	
	UpdateOrderZbResponse addProduct(UpdateOrderZbRequest updateOrderZbRequest);

}
