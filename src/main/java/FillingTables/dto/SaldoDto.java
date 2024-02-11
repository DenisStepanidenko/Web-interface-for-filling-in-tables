package FillingTables.dto;

import java.util.Map;

public class SaldoDto {
    private final int id;
    private final Map<String,Double> allSaldo;

    public SaldoDto(int id, Map<String, Double> allSaldo) {
        this.id = id;
        this.allSaldo = allSaldo;
    }

    public int getId() {
        return id;
    }

    public Map<String, Double> getAllSaldo() {
        return allSaldo;
    }
}
