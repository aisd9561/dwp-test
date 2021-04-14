package dwp.techtest.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "dwp")
public class TechTestApplication {

	private static final Logger LOGGER= LoggerFactory.getLogger(TechTestApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(TechTestApplication.class, args);
	}

}
