package mk;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ApplicationListener {

    @Autowired
	private Tracer tracer;
    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

	 private static Logger log = LoggerFactory.getLogger(ApplicationListener.class);


//	protected Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {

		
		
		System.out.println("ENV variables");
			

			Map<String, String> env = System.getenv();
			for (String envName : env.keySet()) {
				System.out.format("%s=%s%n", envName, env.get(envName));
			}


		
	SpringApplication.run(ApplicationListener.class, args);

	}

	 @StreamListener(WorkUnitsSink.CHANNEL_NAME)
	public void processOrder(WorkUnit order) {


//			log.info("SpanTraceId: " + order.getSpanTraceId());

/*

	  Span newSpan= Span.builder()
					.spanId(1l)
					.traceId(Span.hexToId(order.getSpanTraceId()))
					.parent(Span.hexToId(order.getSpanTraceId()  ))
					.baggage("foo", "bar")
					.name("span")
					.exportable(true).build();
		    
		    //tracer.createSpan(ApplicationListener.class.getName(),newSpan);
		    tracer.continueSpan(newSpan);
	 
//		 Span newSpan = this.tracer.createSpan("calculateTax");
		// ...
			// You can tag a span
//			this.tracer.addTag("taxValue", taxValue);
			// ...
			// You can log an event on a span
//			newSpan.logEvent("taxCalculated");

		    log.info("newSpan.getParents().size(): " + newSpan.getParents().size());
		    log.info("newSpan.getParents().get(0).longValue(): " + newSpan.getParents().get(0).longValue());
		    log.info("Span.hexToId(order.getSpanTraceId()): " + Span.hexToId(order.getSpanTraceId()));
		    log.info("newSpan.getProcessId(): " + newSpan.getProcessId());
		    */
		
		
		log.info("microservice Log1\nLog2\nLog3\nLog4\nLog5\n");

		 
		log.info("Read data: " + order.getParentId());
		log.info("Read data: " + order.getSpanTraceId());
		log.info("Read data: " + order.getId());
		log.info("Read data: " + order.getDefinition());
		log.info("Read data order: " + order);

		log.info("Executing microservice");
		
//		restTemplate = new RestTemplate();

		try {
        User user = restTemplate.getForObject("http://springbootmicroservice:9191/api/get-by-email?email=z@fromlistener.com", User .class);
        log.info("Microservice executed: "+user.getName());
		}//try
		catch(Exception e)
		{
	        log.info("Microservice executing exception: "+e.getMessage());
	        log.info("Microservice executing exception: "+e.getLocalizedMessage());
			
		}

		
		log.info("microservice END Log1\nLog2\nLog3\nLog4\nLog5\n");

		
	}
	
	/*
	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
*/	
}
