package fr.zenika.meteo.meteo;

import fr.zenika.meteo.meteo.features.MeteoFeatures;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

@SpringBootApplication
@EnableFeignClients
public class MeteoApplication {
	@Bean
	public FeatureProvider featureProvider() {
		return new EnumBasedFeatureProvider(MeteoFeatures.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MeteoApplication.class, args);
	}
}
