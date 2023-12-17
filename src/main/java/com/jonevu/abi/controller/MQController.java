package com.jonevu.abi.controller;

import com.jonevu.abi.mq.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/mq")
public class MQController {

    private static final Logger logger = LoggerFactory.getLogger(MQController.class);

    @Autowired
    MQService mqService;

    @GetMapping("/sendcs/{msg}")
    public ResponseEntity<String> sendCS(@PathVariable("msg") String msg) throws JMSException {
        logger.info("Processing sendCS controller ...");
        String msgID = mqService.sendCS(msg);
        return new ResponseEntity<>(msgID, HttpStatus.OK);
    }

    @GetMapping("/send/{msg}")
    public ResponseEntity<String> send(@PathVariable("msg") String msg) throws JMSException {
        logger.info("Processing send controller ...");
        String msgID = mqService.send(msg);
        return new ResponseEntity<>(msgID, HttpStatus.OK);
    }

    @GetMapping(value = "/rcvcs")
    public ResponseEntity<String> receiveCSMQ() {
        logger.info("Processing recvs controller ...");
        String message = mqService.receiveCS();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(value = "/rcv")
    public ResponseEntity<String> receive() throws JMSException {
        logger.info("Processing recv controller ...");
        String message = mqService.receive();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
