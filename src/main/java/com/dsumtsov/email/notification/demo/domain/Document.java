package com.dsumtsov.email.notification.demo.domain;

import lombok.Value;

@Value
public class Document {
    String name;
    byte[] body;
}
