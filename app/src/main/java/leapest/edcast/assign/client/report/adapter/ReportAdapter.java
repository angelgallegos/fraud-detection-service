package leapest.edcast.assign.client.report.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import leapest.edcast.assign.client.report.model.Report;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReportAdapter {
    private final ObjectMapper mapper = new ObjectMapper();

    public String toJsonString(Report report) throws JsonProcessingException {
        return mapper.writeValueAsString(report);
    }

    public String fromJsonString(String reportJson) throws JsonProcessingException {
        JsonNode root = null;
        try {
            root = mapper.readTree(reportJson);
            return root.path("name").toString();
        } catch (IOException e) {
            return null;
        }
    }

}
