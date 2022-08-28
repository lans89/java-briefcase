package com.example.apiconsumer;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiconsumerApplicationTests {

	WireMockServer wireMockServer;
	@LocalServerPort
	int randomServerPort;
	private final String host = "http://localhost:";

	private final Integer mockPort = 8090;
	@BeforeEach
	public void setup () {
		wireMockServer = new WireMockServer(mockPort);
		wireMockServer.start();
		setupStub();
	}

	@AfterEach
	public void teardown () {
		wireMockServer.stop();
	}

	public void setupStub() {
		// Prepara respuesta si consumen servicio de numberconversion.
		wireMockServer.stubFor(get(urlEqualTo("/api/activity/"))
				.willReturn(aResponse()
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withStatus(HttpStatus.OK.value())
						.withBodyFile("response.json")));
	}
	@Test
	void testV1WithRestTemplate(){
		String hostUrl = host+randomServerPort+"/bored/v1/request";
		given().when().get(hostUrl)
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.body("activity", notNullValue())
				.body("key", notNullValue())
				.body("key", equalTo(3943506))
				.log().all();
	}
	@Test
	void testV1WithWebflux(){
		String hostUrl = host+randomServerPort+"/bored/v2/request";
		given().when().get(hostUrl)
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.body("activity", notNullValue())
				.body("key", notNullValue())
				.body("key", equalTo(3943506))
				.log().all();
	}
	@Test
	void testV1WithOkHttp3(){
		String hostUrl = host+randomServerPort+"/bored/v3/request";
		given().when().get(hostUrl)
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.body("activity", notNullValue())
				.body("key", notNullValue())
				.body("key", equalTo(3943506))
				.log().all();
	}
}
