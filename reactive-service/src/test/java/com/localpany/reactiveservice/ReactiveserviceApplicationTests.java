package com.localpany.reactiveservice;

import com.localpany.reactiveservice.model.envelope.EnvelopeCountryInfo;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class ReactiveserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testBodyT() throws Exception {

		Serializer sr = new Persister();
		EnvelopeCountryInfo env  = new EnvelopeCountryInfo();
		var rst = sr.read(EnvelopeCountryInfo.class, new File("ejemplo.xml"));
		System.out.println(rst);
	}

}
