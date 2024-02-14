package FillingTables.dto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultDto {
    private final String personalAccount;
    private final LinkedHashMap<String, Double> charges;
    private final LinkedHashMap<String, List<Double>> payments;
    private final LinkedHashMap<String, Double> saldo;

    public ResultDto(String personalAccount, LinkedHashMap<String, Double> charges, LinkedHashMap<String, List<Double>> payments, LinkedHashMap<String, Double> saldo) {
        this.personalAccount = personalAccount;
        this.charges = charges;
        this.payments = payments;
        this.saldo = saldo;
    }

    public String getPersonalAccount() {
        return personalAccount;
    }

    public LinkedHashMap<String, Double> getCharges() {
        return charges;
    }

    public LinkedHashMap<String, List<Double>> getPayments() {
        return payments;
    }

    public LinkedHashMap<String, Double> getSaldo() {
        return saldo;
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
