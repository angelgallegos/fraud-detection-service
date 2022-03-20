package leapest.edcast.assign.mapper;

import leapest.edcast.assign.client.report.model.Company;
import leapest.edcast.assign.client.report.model.Record;
import leapest.edcast.assign.client.report.model.Report;
import leapest.edcast.assign.entity.Fraud;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReportMapper {

    public Report create(
        Map<String, Long> countByCountry,
        String company,
        Date from,
        Date to,
        Integer overall
    ) {
        List<Record> records = new ArrayList<>();
        RecordMapper recordMapper = new RecordMapper();
        for (Map.Entry<String, Long> entry : countByCountry.entrySet()) {
            records.add(
                 recordMapper.create(entry.getKey(), entry.getValue().floatValue()/overall, entry.getValue())
            );
        }
        return new Report(
            new Company(company),
            records,
            from,
            to,
            overall
        );
    }
}
