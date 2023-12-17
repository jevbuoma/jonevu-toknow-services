package com.jonevu.abi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jonevu.abi.logger.model.AsyncLogServiceRequest;
import com.jonevu.abi.logger.service.AsyncLoggerRequestService;
import com.jonevu.abi.logger.service.AsyncLoggerResponseService;
import com.jonevu.abi.profile.model.Orders;
import com.jonevu.abi.profile.repository.OrdersRepository;
import com.jonevu.abi.util.CommonUtil;


@RestController
@RequestMapping("/orders")
public class OrdersController extends LogController {

    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    private OrdersRepository ordersRepository;

    AsyncLoggerRequestService asyncLoggerRequestService;
    
    AsyncLoggerResponseService asyncLoggerResponseService;

    public OrdersController(OrdersRepository ordersRepository, AsyncLoggerRequestService asyncLoggerRequestService,
                            AsyncLoggerResponseService asyncLoggerResponseService) {
        this.ordersRepository = ordersRepository;
        this.asyncLoggerRequestService = asyncLoggerRequestService;
        this.asyncLoggerResponseService = asyncLoggerResponseService;
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable("id") Long id) {
        logger.info("Getting order for id: "+id);
        Orders order = ordersRepository.findOne(id);
        if (order == null) {
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping(value="/list")
    public ResponseEntity<List<Orders>> getOrders() {
        List<Orders> orderList = new ArrayList<>();
        ordersRepository.findAll().forEach(orderList::add);
        if (orderList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping(value="/count")
    public ResponseEntity<Long> countOrders() {
        Long numOfOrders = ordersRepository.count();
        if (numOfOrders == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(numOfOrders, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable("id") Long id) {
        logger.info("Deleting order for id: "+id);
        ordersRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param order
     * @param uriComp
     * @return
     * @throws JsonProcessingException 
     */
    @RequestMapping(value="/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_XML_VALUE },
    		consumes = { MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Orders> addXmlOrder(@RequestBody Orders order, UriComponentsBuilder uriComp) throws JsonProcessingException {
        // log the order request ..
        String xmlstr = (String)CommonUtil.convertToXmlJson(order, "xml");
        AsyncLogServiceRequest request = loadRequest(xmlstr, "xml", "ORDERS", "ord");
        logger.info("Inside addXmlOrder controller ... requestId="+request.getReqId());
        asyncLoggerRequestService.create(request);
        // ...
        order = ordersRepository.save(order);
        createResponse(request.getReqId(), xmlstr, "xml", "ord", "ORDERS");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComp.path("/id/{id}").buildAndExpand(order.getOrderId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE },
    		consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Orders> addJsonOrder(@RequestBody Orders order, UriComponentsBuilder uriComp) throws JsonProcessingException {
        // log the order request ..
        String xmlstr = (String)CommonUtil.convertToXmlJson(order, "json");
        AsyncLogServiceRequest request = loadRequest(xmlstr, "json", "ORDERS", "ord");
        logger.info("Inside addJsonOrder controller ... requestId="+request.getReqId());
        asyncLoggerRequestService.create(request);
        // ...
        order = ordersRepository.save(order);
        createResponse(request.getReqId(), xmlstr, "json", "ord", "ORDERS");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComp.path("/id/{id}").buildAndExpand(order.getOrderId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
   
    /**
     *
     * @param order
     * @return
     */
    @PutMapping(value="/update")
    public ResponseEntity<Orders> updateOrder( @RequestBody Orders order) {
        ordersRepository.save(order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
