package com.jonevu.abi.controller;


import com.jonevu.abi.logger.service.AsyncLoggerRequestService;
import com.jonevu.abi.logger.service.AsyncLoggerResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jonevu.abi.logger.model.AsyncLogServiceRequest;
import com.jonevu.abi.profile.model.Customers;
import com.jonevu.abi.profile.repository.CustomersRepository;
import com.jonevu.abi.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController extends LogController {

    private static final Logger logger = LoggerFactory.getLogger(CustomersController.class);

    private CustomersRepository customersRepository;

    AsyncLoggerRequestService asyncLoggerRequestService;

    AsyncLoggerResponseService asyncLoggerResponseService;

    public CustomersController(CustomersRepository customersRepository, AsyncLoggerRequestService asyncLoggerRequestService,
                               AsyncLoggerResponseService asyncLoggerResponseService) {
        this.customersRepository = customersRepository;
        this.asyncLoggerRequestService = asyncLoggerRequestService;
        this.asyncLoggerResponseService = asyncLoggerResponseService;
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") Long id) {
        logger.info("Getting customer for id: "+id);
        Customers customer = customersRepository.findOne(id);
        if (customer == null) {
            return new ResponseEntity<>("id: "+id+" Not Found!!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<?> getCustomers() {
        List<Customers> customerList = new ArrayList<>();
        customersRepository.findAll().forEach(customerList::add);
        if (customerList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/count")
    public ResponseEntity<?> countCustomers() {
        Long numOfCustomers = customersRepository.count();
        if (numOfCustomers == 0)
            return new ResponseEntity<>("Not Found!!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(numOfCustomers, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        customersRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param customer
     * @param uriComp
     * @return
     * @throws JsonProcessingException 
     */
    @RequestMapping(value="/create", method = RequestMethod.POST, produces = { MediaType.APPLICATION_XML_VALUE },
    		consumes = { MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> createXmlCustomer(@RequestBody Customers customer, UriComponentsBuilder uriComp) throws JsonProcessingException {
        // log the customer request ..
        String xmlstr = (String)CommonUtil.convertToXmlJson(customer, "xml");
        AsyncLogServiceRequest request = loadRequest(xmlstr, "xml", "CUSTOMERS", "cus");
        logger.info("Inside add Xml Customer controller ... requestId="+request.getReqId());
        asyncLoggerRequestService.create(request);
        // ...  	    	
        customer = customersRepository.save(customer);
        createResponse(request.getReqId(), xmlstr, "xml", "cus", "CUSTOMERS");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComp.path("/customers/id{id}").buildAndExpand(customer.getCustomerId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    /**
    *
    * @param customer
    * @param uriComp
    * @return
     * @throws JsonProcessingException 
    */
    @RequestMapping(value="/create", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE },
    		consumes = { MediaType.APPLICATION_JSON_VALUE })
   public ResponseEntity<?> createJsonCustomer(@RequestBody Customers customer, UriComponentsBuilder uriComp) throws JsonProcessingException {
        // log the customer request ..
        String jsonstr = (String)CommonUtil.convertToXmlJson(customer, "json");
        AsyncLogServiceRequest request = loadRequest(jsonstr, "json", "CUSTOMERS", "cus");
        logger.info("Inside add JSON Customer controller ... requestId="+request.getReqId());
        asyncLoggerRequestService.create(request);
        // ...  	
       customer = customersRepository.save(customer);
       createResponse(request.getReqId(), jsonstr, "json", "cus", "CUSTOMERS");
       HttpHeaders headers = new HttpHeaders();
       headers.setLocation(uriComp.path("/customers/id{id}").buildAndExpand(customer.getCustomerId()).toUri());
       return new ResponseEntity<String>(headers, HttpStatus.CREATED);
   }  
}
