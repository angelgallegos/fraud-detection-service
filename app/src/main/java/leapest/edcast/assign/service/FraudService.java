package leapest.edcast.assign.service;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import com.fasterxml.jackson.core.JsonProcessingException;
import leapest.edcast.assign.client.report.service.ReportService;
import leapest.edcast.assign.filter.FraudCriteria;
import leapest.edcast.assign.filter.FraudReportCriteria;
import leapest.edcast.assign.entity.Fraud;
import leapest.edcast.assign.client.report.ReportRequest;
import leapest.edcast.assign.client.report.adapter.ReportAdapter;
import leapest.edcast.assign.client.report.model.Company;
import leapest.edcast.assign.client.report.model.Record;
import leapest.edcast.assign.client.report.model.Report;
import leapest.edcast.assign.mapper.RecordMapper;
import leapest.edcast.assign.mapper.ReportMapper;
import leapest.edcast.assign.repository.FraudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FraudService {
    @Autowired
    FraudRepository repository;

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    ReportService reportService;

    public Optional<Fraud> get(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Fraud create(Fraud fraud) {
        repository.save(fraud);
        return fraud;
    }

    public List<Fraud> findAll() {

        return (List<Fraud>) repository.findAll();
    }

    public List<Fraud> findByCriteria(FraudCriteria fraudCriteria) {
        Pageable page = PageRequest.of(fraudCriteria.getPage(), fraudCriteria.getSize());
        return repository.findAllByCriteria(
            fraudCriteria.getFrom(),
            fraudCriteria.getTo(),
            page
        );
    }

    public String generateReport(FraudReportCriteria fraudReportCriteria) {
        List<Fraud> fraudList = this.findByReportCriteria(fraudReportCriteria);
        int totalFraudReports = fraudList.size();

        if (fraudList.isEmpty()) {
            return null;
        }

        Map<String, Long> countryCount = fraudList.stream().collect(groupingBy(Fraud::getCountry, counting()));

        Report report = reportMapper.create(
            countryCount,
            fraudReportCriteria.getCompany(),
            fraudReportCriteria.getFrom(),
            fraudReportCriteria.getTo(),
            totalFraudReports
        );

        return reportService.createReport(report);
    }

    public List<Fraud> findByReportCriteria(FraudReportCriteria fraudReportCriteria) {
        return repository.findAllByReportCriteria(
                fraudReportCriteria.getFrom(),
                fraudReportCriteria.getTo(),
                fraudReportCriteria.getCompany()
        );
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
