package com.jonevu.abi.mq.service.impl;

import com.jonevu.abi.mq.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MQServiceImpl implements MQService {

    private static final Logger logger = LoggerFactory.getLogger(MQServiceImpl.class);

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public String sendCS(String message) {
        jmsTemplate.convertAndSend(message);
        int hash_code = jmsTemplate.hashCode();
        logger.info("Processed hash-code returned in sendcs controller: "+hash_code);
        return String.valueOf(hash_code);
    }

    @Override
    public String send(String message) {
        jmsTemplate.send(session -> {
            TextMessage message_ = session.createTextMessage(message);
            return message_;
        });
        int hash_code = jmsTemplate.hashCode();
        logger.info("Processed hash-code returned in send controller: "+hash_code);
        return String.valueOf(hash_code);
    }

    @Override
    public String receiveCS() {
        String message = (String)jmsTemplate.receiveAndConvert();
        logger.debug("Received message from receiveCS: "+message);
        return message;
    }

    @Override
    public String receive() throws JMSException {
        Message message = jmsTemplate.receive();
        String msg = message.getBody(String.class);
        logger.debug("Received message from receiveCS: "+msg);
        return msg;
    }
}
