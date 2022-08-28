package com.example.wsdlconsumer;

import com.example.wsdlconsumer.model.CountryInfoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Map;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class WsdlconsumerApplicationTests {
	@LocalServerPort
	int randomServerPort;

	@Autowired
	private TestRestTemplate restTemplate;

	private final String hostUrl = "http://localhost:";
	private final String server1 = "/number/convert/";

	private final String server2= "/country/info/";

	@Test
	void convertNumbersConnected() throws Exception{
		String numberTest = "5000";
		String resultExpected = "five thousand";
		ResponseEntity<Map> response = restTemplate.getForEntity(
				new URI( hostUrl + randomServerPort + server1 + numberTest).toString(),
				Map.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(resultExpected, response.getBody().get("Word"));
	}

	@Test
	void countryInfoConnected() throws Exception {
		String codeCountry = "HN";
		String resultExpected = "Honduras";
		ResponseEntity<CountryInfoDTO> response = restTemplate.getForEntity(
				new URI(hostUrl + randomServerPort + server2 + codeCountry).toString(),
				CountryInfoDTO.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(resultExpected, response.getBody().getName());
	}
}