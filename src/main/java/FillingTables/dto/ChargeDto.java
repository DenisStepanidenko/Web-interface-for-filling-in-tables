package FillingTables.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChargeDto {
    private final Map<String, Double> allCharges;
    private final int id;

    public ChargeDto(int id , Map<String, Double> allCharges) {
        this.allCharges = allCharges;
        this.id = id;
    }

    public Map<String, Double> getAllCharges() {
        return allCharges;
    }

    public int getId() {
        return id;
    }
}
