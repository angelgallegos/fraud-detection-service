package leapest.edcast.assign.entity;

import java.sql.*;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonFormat;
import leapest.edcast.assign.entity.json.views.Views;
import leapest.edcast.assign.entity.validation.groups.OnCreate;
import leapest.edcast.assign.entity.validation.groups.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Fraud {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonView(Views.Internal.class)
    private Long id;

    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @NotBlank(message = "Country is mandatory")
    @JsonView(Views.Public.class)
    private String country;

    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @NotBlank(message = "Card is mandatory")
    @JsonView(Views.Public.class)
    private String card;

    @NotBlank(message = "Company is mandatory")
    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @JsonView(Views.Public.class)
    private String company;

    @Column(name = "crated_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(Views.Public.class)
    private Date date;

    public Fraud() {
        super();
    }

    public Fraud(Long id, String country, String card, String company, Date date) {
        super();
        this.id = id;
        this.country = country;
        this.card = card;
        this.company = company;
        this.date = date;
    }

    public Fraud(String country, String card, String company, Date date) {
        super();
        this.country = country;
        this.card = card;
        this.company = company;
        this.date = date;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Fraud reported [id=%s, card=%s, country=%s]", id, card, country);
    }
}
