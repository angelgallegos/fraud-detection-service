package leapest.edcast.assign.client.report;

import leapest.edcast.assign.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ReportRequest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Config config;

    public String createReport(String reportJson) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(reportJson, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(
             config.getHostName()+":"+config.getPort()+"/api/generate",
                request,
                String.class
        );

        return responseEntityStr.getBody();
    }
}
