package com.jonevu.abi.logger.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.jonevu.abi.profile.model.Orders;
import com.jonevu.abi.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrdersControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(OrdersControllerTest.class);

    @Test
    @Ignore
    public void getOrderTest() {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8080/orders/id/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("Order", headers);
        ResponseEntity<Orders> order = restTemplate.exchange(uri, HttpMethod.GET, entity, Orders.class, 28L);
        ResponseEntity<Orders>order_ = restTemplate.getForEntity(uri, Orders.class, 28L);
        logger.info("ID: "+order.getBody().getOrderId());
        logger.info("Order Mode: "+order.getBody().getOrderMode());
        logger.info("Order Total: "+order.getBody().getOrderTotal());
        logger.info("");
        logger.info("Using getForEntity object ...");
        logger.info("ID: "+order_.getBody().getOrderId());
        logger.info("Order Mode: "+order_.getBody().getOrderMode());
        logger.info("Order Total: "+order_.getBody().getOrderTotal());
    }

    @Test
    @Ignore
    public void getOrdersTest() {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8080/orders/list";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("Orders", headers);
        ResponseEntity<Orders[]> responseEntity = restTemplate.getForEntity(uri, Orders[].class);
        logger.info("responseEntity: " + responseEntity.toString());
        ResponseEntity<List<Orders>> rateResponse =
                restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Orders>>() {
                        });
        List<Orders> ordersList = rateResponse.getBody();
        logger.info("Orders size: "+ordersList.size()+" ...Orders contents: "+ordersList.toString());
    }

    @Test
    @Ignore
    public void getOrdersCountTest() {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8080/orders/count";
        ResponseEntity<Long> orders = restTemplate.getForEntity(uri, Long.class);
        logger.info("Orders count: "+orders.getBody().longValue());
    }

    @Test
    @Ignore    // already deleted!!
    public void deleteOrderTest() {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8080/orders/delete/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("Order", headers);
        ResponseEntity<Orders> order = restTemplate.exchange(uri, HttpMethod.DELETE, entity, Orders.class, 11);
        logger.info("Delete status code is: "+order.getStatusCode().value());
    }

    @Test
    @Ignore     // alreaded added / created!!!
    public void addOrderTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/orders/add";
        Orders order = createOrder(); //
        HttpEntity<Orders> responseEntity = new HttpEntity<>(order, headers);
        URI uri= restTemplate.postForLocation(url, responseEntity);
        logger.info("ReponseEntity object: "+responseEntity);
        logger.info("Newly created order ID path: "+uri.getPath());
    }

    @Test
    @Ignore    // already update!!
    public void updateOrderTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/orders/update";
        Orders order = updateOrder();
        HttpEntity<Orders> requestEntity = new HttpEntity<>(order, headers);
        restTemplate.put(url, requestEntity);
        logger.info("Update ReponseEntity object: "+requestEntity);
    }

    /**
     *
     * @return
     */
    private Orders createOrder() {
        Orders order = new Orders();
        order.setCustomerId(101L);
        order.setOrderDate(DateUtil.getCurrentDate());
        order.setOrderMode("online");
        order.setOrderStatus(9);
        order.setOrderTotal(new BigDecimal(877.03));
        order.setPromotionId(1679L);
        order.setSalesRepId(1112L);
        order.setCreatedDt(DateUtil.getCurrentDate());
        order.setLastUpdatedBy(612L);
        order.setLastUpdatedDt(DateUtil.getCurrentDate());
        order.setSource("YEQ");

        return order;
    }

    private Orders updateOrder() {
        Orders order = new Orders();
        order.setOrderId(29L);
        order.setCustomerId(101L);
        order.setOrderDate(DateUtil.addToDate(-2));
        order.setOrderMode("direct");
        order.setOrderStatus(4);
        order.setOrderTotal(new BigDecimal(2511.95));
        order.setPromotionId(1289L);
        order.setSalesRepId(1837L);
        order.setCreatedDt(DateUtil.addToDate(-2));
        order.setLastUpdatedBy(612L);
        order.setLastUpdatedDt(DateUtil.addToDate(-2));
        order.setSource("JE92");

        return order;

    }
}
