package leapest.edcast.assign.client.report.model;

public class Record {
    private String country;

    private Float percentage;

    private Long total;

    public Record(String country, Float percentage, Long total) {
        this.country = country;
        this.percentage = percentage;
        this.total = total;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
