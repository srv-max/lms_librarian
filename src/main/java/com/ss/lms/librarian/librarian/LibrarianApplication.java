package com.ss.lms.librarian.librarian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarianApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(LibrarianApplication.class, args);
		System.out.println("lms_librarian running");
	}

}
