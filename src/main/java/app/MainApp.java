package app;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
//@EnableAutoConfiguration	
public class MainApp {

	public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(MainApp.class, args);
	}
}
