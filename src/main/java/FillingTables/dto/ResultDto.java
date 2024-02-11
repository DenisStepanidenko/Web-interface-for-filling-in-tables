package FillingTables.dto;

import java.util.Map;

public class ResultDto {
    private final String personalAccount;
    private final Map<String,Double> charges;
    private final Map<String,Double> payments;
    private final Map<String,Double> saldo;

    public ResultDto(String personalAccount, Map<String, Double> charges, Map<String, Double> payments, Map<String, Double> saldo) {
        this.personalAccount = personalAccount;
        this.charges = charges;
        this.payments = payments;
        this.saldo = saldo;
    }

    public Map<String, Double> getCharges() {
        return charges;
    }

    public Map<String, Double> getPayments() {
        return payments;
    }

    public Map<String, Double> getSaldo() {
        return saldo;
    }

    public String getPersonalAccount() {
        return personalAccount;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "personalAccount='" + personalAccount + '\'' +
                ", charges=" + charges +
                ", payments=" + payments +
                ", saldo=" + saldo +
                '}';
    }
}
