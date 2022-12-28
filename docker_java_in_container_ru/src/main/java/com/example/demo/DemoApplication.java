package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Resp {
	private String message;
}

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/search")
class api {

	@Value("${someprop1.value}")
	private String someprop1;

	@Value("${someprop2.value}")
	private String someprop2;

	@GetMapping
	public Resp pingMessage(@RequestParam(name = "q") String q) {
		log.info("message : " + q);
		log.info("props : " + someprop1);
		log.info("props : " + someprop2);
		return Resp.builder().message(someprop1 + " : " + q).build();
	}
}


@EnableWebMvc
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
