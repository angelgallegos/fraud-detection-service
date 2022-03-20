package leapest.edcast.assign.repository;

import leapest.edcast.assign.entity.Fraud;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

@Repository
public interface FraudRepository extends PagingAndSortingRepository<Fraud, Long> {
    @Query("select f from Fraud f where f.date >= :from and f.date <= :to")
    List<Fraud> findAllByCriteria(
        @Param("from") Date from,
        @Param("to") Date to,
        Pageable pageable
    );

    @Query("select f from Fraud f where f.company = :company and f.date >= :from and f.date <= :to")
    List<Fraud> findAllByReportCriteria(
        @Param("from") Date from,
        @Param("to") Date to,
        @Param("company") String company
    );
}