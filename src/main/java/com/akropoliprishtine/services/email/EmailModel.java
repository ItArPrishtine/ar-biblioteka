package com.akropoliprishtine.services.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailModel {
    Sender sender;
    To to;
    String name;
    String htmlContent;
    String subject;

    @Getter
    @Setter
    public static class Sender {
        String name;
        String email;
    }

    @Getter
    @Setter
    public static class To {
        String name;
        String email;
    }
}
