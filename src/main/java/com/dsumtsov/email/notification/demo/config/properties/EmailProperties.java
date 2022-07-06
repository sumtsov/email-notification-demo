package com.dsumtsov.email.notification.demo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(
        value = "app.email",
        ignoreInvalidFields = true
)
public class EmailProperties {

    private List<String> recipients;
    private String from;
}
