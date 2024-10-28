package me.yeunikey.contester;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ContesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContesterApplication.class, args);
	}

	public static Gson gson() {
		return new Gson();
	}

}
