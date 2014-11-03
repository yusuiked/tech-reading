package sample.di;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class CustomEventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextRefreshedEvent) {
            System.out.println("*** ContextRefreshedEvent! ***");
        } else if (applicationEvent instanceof ContextClosedEvent) {
            System.out.println("*** ContextClosedEvent! ***");
        } else {
            System.out.println("*** Event? ***");
        }
    }
}
