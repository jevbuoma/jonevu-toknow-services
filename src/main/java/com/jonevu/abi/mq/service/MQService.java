package com.jonevu.abi.mq.service;

import javax.jms.JMSException;

public interface MQService {

    String sendCS(String message);

    String send(String message) ;

    String receiveCS();

    String receive() throws JMSException;
}
