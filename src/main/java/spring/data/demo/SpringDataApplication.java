package spring.data.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@GetMapping("/geofence")
	public String restController(){
		return "{\n" +
				"\t\"type\" : \"FeatureCollection\",\n" +
				"\t\"features\" : [\n" +
				"            {\n" +
				"\t\t\"id\": \"K17_R1.039\",\n" +
				"\t\t\"type\": \"Feature\",\n" +
				"\t\t\"feature_type\": \"geofence\",\n" +
				"\t\t\"properties\": {\n" +
				"\t\t\t\"category\": \"geofence\",\n" +
				"\t\t\t\"level_ids\": [\"K17_L1\"]\n" +
				"\t\t},\n" +
				"\t\t\"geometry\": {\n" +
				"\t\t\t\"type\": \"MultiPolygon\",\n" +
				"\t\t\t\"coordinates\": [\n" +
				"\t\t\t\t[\n" +
				"\t\t\t\t\t[\n" +
				"\t\t\t\t\t\t\t[18, 4], [18, 5], [17, 5], [17, 4], [18, 4]\n" +
				"\t\t\t\t\t]\n" +
				"\t\t\t\t]\n" +
				"\t\t\t]\n" +
				"\t\t}\n" +
				"\t}\n" +
				"]\n" +
				"}";
	}
}
