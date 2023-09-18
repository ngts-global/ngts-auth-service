package com.ngts.common.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    @Value("${courier.id}")
    public String courierId;

}
