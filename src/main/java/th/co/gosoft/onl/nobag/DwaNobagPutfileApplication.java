package th.co.gosoft.onl.nobag;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DwaNobagPutfileApplication {

	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\nobag\\filedwa.json");
		byte[] fileContent = Files.readAllBytes(file.toPath());
		final String URL_PUTAWS = "";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "");
  
        HttpEntity<byte[]> entity = new HttpEntity<>(fileContent, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(URL_PUTAWS, HttpMethod.PUT, entity, String.class);
       
        System.out.println("HttpStatus: "+ response.getStatusCode());
        System.out.println("Body: "+ response.getBody());
	}
}
