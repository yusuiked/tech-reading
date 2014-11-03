package sample.di.business.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceImpl implements MessageService {

    @Value("#{msgProperties.message}")
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
