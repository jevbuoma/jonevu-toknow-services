package com.jonevu.abi.mq.config;

import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.JMSException;

@Configuration
@EnableJms
public class JMSMQConfig {
    @Value("${ibm.mq.queue-manager}")
    public String queueManager;

    @Value("${ibm.mq.channel}")
    public String channel;

    @Value("${ibm.mq.hostname}")
    public String hostname;

    @Value("${ibm.mq.ssl-cipher-suite}")
    public String cipherSuite;

    @Value("${ibm.mq.port}")
    public int port;

    @Value("${ibm.mq.transportType}")
    public int transportType;

    @Value("${ibm.mq.sendQueue}")
    public String outQueue;

    @Value("${ibm.mq.receiveTimeout}")
    public long receiveTimeout;

    @Value("${ibm.mq.user}")
    public String user;

    @Value("${ibm.mq.password}")
    public String password;

    @Value("${ibm.mq.conn-name}")
    public String connName;


    @Bean
    public MQQueueConnectionFactory mqConnectionFactory() throws JMSException {
        MQQueueConnectionFactory mqConnectionFactory = new MQQueueConnectionFactory();
        mqConnectionFactory.setHostName(hostname);
        mqConnectionFactory.setConnectionNameList(connName);
        mqConnectionFactory.setChannel(channel);
        mqConnectionFactory.setQueueManager(queueManager);
        mqConnectionFactory.setPort(port);
        mqConnectionFactory.setTransportType(transportType);

        return mqConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory connectionFactory() throws JMSException {
        return new CachingConnectionFactory(mqConnectionFactory());
    }

    @Bean
    public UserCredentialsConnectionFactoryAdapter userCredsConnectionFactory() throws JMSException {
        UserCredentialsConnectionFactoryAdapter ucConnFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        ucConnFactoryAdapter.setTargetConnectionFactory(mqConnectionFactory());
        ucConnFactoryAdapter.setUsername(user);
        ucConnFactoryAdapter.setPassword(password);

        return ucConnFactoryAdapter;
    }

    @Bean
    public DynamicDestinationResolver jmsDestinationResolver() {
        return new DynamicDestinationResolver();
    }

    @Bean
    public MQQueue jmsDestination() throws JMSException {
        return new MQQueue(outQueue);
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(userCredsConnectionFactory());
        jmsTemplate.setDestinationResolver(jmsDestinationResolver());
        jmsTemplate.setDefaultDestination(jmsDestination());
        jmsTemplate.setReceiveTimeout(receiveTimeout);

        return jmsTemplate;
    }
}
