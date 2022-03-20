package leapest.edcast.assign.mapper;

import leapest.edcast.assign.client.report.model.Record;

public class RecordMapper {
    public Record create(String country, Float percentage, Long total) {
        return new Record(country, percentage, total);
    }
}
