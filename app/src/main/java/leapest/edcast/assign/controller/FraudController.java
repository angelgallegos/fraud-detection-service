package leapest.edcast.assign.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.validation.Valid;

import leapest.edcast.assign.filter.FraudCriteria;
import leapest.edcast.assign.filter.FraudReportCriteria;
import leapest.edcast.assign.entity.Fraud;
import leapest.edcast.assign.entity.json.views.Views;
import leapest.edcast.assign.entity.validation.groups.OnCreate;
import leapest.edcast.assign.service.FraudService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("fraud")
public class FraudController extends BaseController {
    @Autowired
    FraudService fraudService;

    @GetMapping("/{id}")
    @JsonView(Views.Internal.class)
    public ResponseEntity<Fraud> request(@PathVariable("id") Long id) {
        return fraudService.get(id)
                .map( fraud -> ResponseEntity.ok().body(fraud) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @Validated(OnCreate.class)
    @JsonView(Views.Internal.class)
    @PostMapping
    public ResponseEntity<Fraud> create(@Valid @RequestBody Fraud fraud) throws URISyntaxException {
        Fraud createdFraud = fraudService.create(fraud);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdFraud.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdFraud);
    }


    @JsonView(Views.Public.class)
    @GetMapping("/list")
    public ResponseEntity<List<Fraud>> list(FraudCriteria fraudCriteria) {
        List<Fraud> frauds = fraudService.findByCriteria(fraudCriteria);
        if (frauds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(frauds);
    }

    @PostMapping("/report/generate")
    public ResponseEntity<String> reportOther(@RequestBody FraudReportCriteria fraudReportCriteria) {
        String report = fraudService.generateReport(fraudReportCriteria);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(report);
    }
}
