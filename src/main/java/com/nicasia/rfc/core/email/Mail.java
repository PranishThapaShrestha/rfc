package com.nicasia.rfc.core.email;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class Mail {
    private String from;
    private String to;
    private String content;
    private String subject;
    private Map<String,Object> model;
}
