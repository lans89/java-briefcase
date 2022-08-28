package com.example.wsdlconsumer;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


// Se asigna un perfil para la prueba, que contiene sus propias propiedades.
@ActiveProfiles("test")
// Se asignara un puerto aleatorio al levantarse el servicio rest de nuestro controller.
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class WsdlconsumerApplicationWithWireMockTests {
	WireMockServer wireMockServer;
	@LocalServerPort
	int randomServerPort;
	private final String hostUrl = "http://localhost:";
	private final String server1 = "/number/convert/";
	private final String server2= "/country/info/";
	private final String APPLICATION_SOAP_XML="application/soap+xml";
	private Map<Integer, MockedService> resourceWeb;
	private final Integer mockPort = 8090;

	@BeforeEach
	public void setup () {
		resourceWeb = new HashMap<>();
		resourceWeb.put(1, MockedService.builder()
				.fileBody("NumberResponse.xml")
				.resource("/webservicesserver/numberconversion.wso?WSDL")
				.build());
		resourceWeb.put(2, MockedService.builder()
				.fileBody("CountryHNInfo.xml")
				.resource("/websamples.countryinfo/CountryInfoService.wso?WSDL")
				.build());
		// Se inicia un WireMock Server para simular los servicios web SOAP para las pruebas.
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
		wireMockServer.stubFor(post(urlEqualTo(resourceWeb.get(1).getResource()))
				.willReturn(aResponse()
						.withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_SOAP_XML)
						.withStatus(HttpStatus.OK.value())
						.withBodyFile(resourceWeb.get(1).getFileBody())));
		// Prepara respuesta si consumen servicio de countryInfo.
		wireMockServer.stubFor(post(urlEqualTo(resourceWeb.get(2).getResource()))
				.willReturn(aResponse()
						.withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_SOAP_XML)
						.withStatus(HttpStatus.OK.value())
						.withBodyFile(resourceWeb.get(2).getFileBody())));
	}

	@Test
	void convertNumbersConnected(){
		String numberTest = "5000";
		String expectedResult = "five thousand";

		given().when().get(hostUrl + randomServerPort + server1 + numberTest)
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.body("Word", notNullValue())
				.body("Word", equalTo(expectedResult)).log().all();

	}

	@Test
	void countryInfoConnected(){
		String codeTest = "HN";
		String expectedResult = "Honduras";
		String expectedResult2 = "Spanish";

		given().when().get(hostUrl + randomServerPort + server2 + codeTest)
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.body("name", notNullValue())
				.body("name", equalTo(expectedResult))
				.body("languages", not(emptyArray()))
				.body("languages[0].name", equalTo(expectedResult2)).log().all();

	}

}