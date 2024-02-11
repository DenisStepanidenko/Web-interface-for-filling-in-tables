package FillingTables.dto;

import java.util.Map;

public class PaymentDto {
    private int id;
    private final Map<String,Double> payments;

    public PaymentDto(int id, Map<String, Double> payments) {
        this.id = id;
        this.payments = payments;
    }

    public int getId() {
        return id;
    }

    public Map<String, Double> getPayments() {
        return payments;
    }
}
