package ma.lydec.gestioncompte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "ma.lydec.gestioncompte.entities")

public class GestioncompteApplication {

	public static void main(String[] args) {

		SpringApplication.run(GestioncompteApplication.class, args);
	}

}
