package leapest.edcast.assign.repository;

import leapest.edcast.assign.filter.FraudCriteria;
import leapest.edcast.assign.entity.Fraud;
import java.util.List;

public interface CustomFraudRepository {
    List<Fraud> findAllByCriteria(FraudCriteria criteria);
}

