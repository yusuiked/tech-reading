package com.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenPassword {
	public static void main(String[] args) {
		System.out.printf(new BCryptPasswordEncoder().encode("demo"));
	}
}
