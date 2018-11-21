package th.co.gosoft.onl.nobag;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DwaNobagPutfileApplication {

	public static void main(String[] args) throws IOException, Exception, KeyStoreException, NoSuchAlgorithmException {
		File file = new File("C:\\Users\\tanaponjit\\Documents\\Works\\NoBag\\filedwa.json");
		byte[] fileContent = Files.readAllBytes(file.toPath());
		final String URL_PUTAWS = "https://m6e9ozq9le.execute-api.ap-southeast-1.amazonaws.com/devstage/nobagfile/d-nethichaiam-nobagfile-bucket-01/nobagfile.json";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "eP4il3qnOMaOcyvVeVW8O7SXqZUR4F7n1QfrJMaP");
        
        HttpEntity<byte[]> entity = new HttpEntity<>(fileContent, headers);
        
       //RestTemplate restTemplate = new RestTemplate();
        
        RestTemplate restTemplate = restTemplate();
        
        ResponseEntity<String> response = restTemplate.exchange(URL_PUTAWS, HttpMethod.PUT, entity, String.class);
       
        System.out.println("HttpStatus: "+ response.getStatusCode());
        System.out.println("Body: "+ response.getBody());
	}
	
	
	@Bean
	public static RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
	                    .loadTrustMaterial(null, acceptingTrustStrategy)
	                    .build();

	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);    

	    CloseableHttpClient httpClient = HttpClients.custom()
	                    .setSSLSocketFactory(csf)
	                    .build();
    
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    
	    /*HttpComponentsClientHttpRequestFactory requestFactory 
	    = new HttpComponentsClientHttpRequestFactory(
	        HttpClientBuilder.create()
	                .setProxy(new HttpHost("myproxy.com", 80, "http"))
	                .build());
		*/
	    
	    requestFactory.setHttpClient(httpClient);
	    
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    return restTemplate;
	 }
	 
}
