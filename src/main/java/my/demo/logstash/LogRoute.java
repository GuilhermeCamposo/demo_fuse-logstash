package my.demo.logstash;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class LogRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:logger?period=5s")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getMessage().setBody("My message UUID: " + UUID.randomUUID().toString());
                    }
                })
        .log("${body}");

    }

}
