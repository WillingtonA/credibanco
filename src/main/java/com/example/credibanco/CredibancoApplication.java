package com.example.credibanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan({"com.example.credibanco"})
public class CredibancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CredibancoApplication.class, args);
	}

}
