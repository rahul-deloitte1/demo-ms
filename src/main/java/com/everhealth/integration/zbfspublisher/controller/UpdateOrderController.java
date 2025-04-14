package com.everhealth.integration.zbfspublisher.controller;

import com.everhealth.integration.zbfspublisher.modal.UpdateOrderZbRequest;
import com.everhealth.integration.zbfspublisher.modal.UpdateOrderZbResponse;
import com.everhealth.integration.zbfspublisher.service.UpdateOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UpdateOrderController {
	
	Logger LOG = LoggerFactory.getLogger(UpdateOrderController.class);
	
	@Autowired
	public UpdateOrderService updateOrderService;

    public UpdateOrderController(UpdateOrderService updateOrderService) {
		this.updateOrderService = updateOrderService;
	}

	@PostMapping("/v1/update-order/add-product")
    public ResponseEntity<UpdateOrderZbResponse> addProduct(@RequestBody UpdateOrderZbRequest updateOrderZbRequest){
    	
    	LOG.info("UpdateOrderController - Inside addProduct");
    	// perform request validations on mandatory JSON attributes
    	UpdateOrderZbResponse updateOrderZbResponse = updateOrderService.addProduct(updateOrderZbRequest);
    	
    	LOG.info("UpdateOrderController - Exiting addProduct");
        return new ResponseEntity<>(updateOrderZbResponse, HttpStatus.OK);

    }
}
