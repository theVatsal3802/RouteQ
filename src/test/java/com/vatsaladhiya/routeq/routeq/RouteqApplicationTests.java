package com.vatsaladhiya.routeq.routeq;

import com.vatsaladhiya.routeq.routeq.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RouteqApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void sendEmailTest() {
		emailSenderService.sendEmail("tehofix691@macsnipe.com", "Hello", "Hi, Welcome to RouteQ!\n\nHope you enjoy our app.");
	}

	@Test
	void sendBulkEmailTest() {
		String[] emails = {
				"tehofix691@macsnipe.com"
		};
		emailSenderService.sendBulkEmail(emails, "Hello", "Hi, Welcome to RouteQ!\n\nHope you enjoy our app.");
	}

	@Test
	void contextLoads() {
	}

}
