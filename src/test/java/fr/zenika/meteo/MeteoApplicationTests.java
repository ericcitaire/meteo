package fr.zenika.meteo;

import fr.zenika.meteo.owm.api.OpenWeatherMapAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeteoApplicationTests {
	@MockBean
	private OpenWeatherMapAPI openWeatherMapAPI;

	@Test
	public void contextLoads() {
	}
}
