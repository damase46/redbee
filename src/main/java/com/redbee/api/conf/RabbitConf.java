package com.redbee.api.conf;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@EnableRabbit
public class RabbitConf implements RabbitListenerConfigurer{

    public static final String EXCHANGE_NAME = "appExchange";
    public static final String QUEUE_GENERIC_NAME = "queuePersist";
    public static final String ROUTING_KEY = "messages.key";

    public static final String QUEUE_COMMENT = "queueComment";
    public static final String QUEUE_CUSTOM_COMMENT = "queueCustomComment";
    public static final String QUEUE_HOTEL = "queueHotel";
    public static final String QUEUE_CUSTOM_HOTEL = "queueCustomHotel";

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public DirectExchange appExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueGeneric() {
        return new Queue(QUEUE_GENERIC_NAME);
    }

    @Bean
    public Queue queueComment() {
        return new Queue(QUEUE_COMMENT);
    }

    @Bean
    public Queue queueCustomComment() {
        return new Queue(QUEUE_CUSTOM_COMMENT);
    }

    @Bean
    public Queue queueCustomHotel() {
        return new Queue(QUEUE_CUSTOM_HOTEL);
    }

    @Bean
    public Queue queueHotel() {
        return new Queue(QUEUE_HOTEL);
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public Binding declareBindingGeneric() { return BindingBuilder.bind(appQueueGeneric()).to(appExchange()).with(ROUTING_KEY); }

    @Bean
    public Binding commentBinding() {
        return BindingBuilder.bind(queueComment()).to(appExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding commentCustomBinding() {
        return BindingBuilder.bind(queueCustomComment()).to(appExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding hotelBinding() {
        return BindingBuilder.bind(queueHotel()).to(appExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding hotelCustomBinding() {
        return BindingBuilder.bind(queueCustomHotel()).to(appExchange()).with(ROUTING_KEY);
    }
}
