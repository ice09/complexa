package tech.indus340.complexa;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.indus340.complexa.testing.CucumberTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {CucumberTest.class})
public class ComplexaApplicationTests {

	@Test
	void contextLoads() {
		throw new IllegalStateException("blabla");
	}

}
