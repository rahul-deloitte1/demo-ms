package com.everhealth.integration.zbfspublisher.controller;

import com.everhealth.integration.zbfspublisher.modal.ZuoraRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@CrossOrigin(origins = "*")
public class TestController {

    Logger LOG = LoggerFactory.getLogger(TestController.class);
    @GetMapping("v1/hello")
    public ResponseEntity<String> hello() {

        LOG.info("inside the hello example");
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("v1/helloPost")
    public ResponseEntity<String> helloPost(@RequestBody ZuoraRequest zuoraRequest) {

        LOG.info("inside the helloPost example");
        LOG.info("order ID " + zuoraRequest.getOrderId());
        LOG.info(("subscription ID " + zuoraRequest.getSubscriptionId()));

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}