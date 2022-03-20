package leapest.edcast.assign.client.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import leapest.edcast.assign.client.report.ReportRequest;
import leapest.edcast.assign.client.report.adapter.ReportAdapter;
import leapest.edcast.assign.client.report.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    ReportRequest reportRequest;

    @Autowired
    ReportAdapter reportAdapter;

    public String createReport(Report report) {
        try {
            return reportRequest.createReport(reportAdapter.toJsonString(report));
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
