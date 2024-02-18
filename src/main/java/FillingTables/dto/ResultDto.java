package FillingTables.dto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultDto {
    private final String personalAccount;
    private final String initialSaldo;
    public final List<String> description;

    public ResultDto(String personalAccount, String initialSaldo, List<String> description) {
        this.personalAccount = personalAccount;
        this.initialSaldo = initialSaldo;
        this.description = description;
    }

    public String getPersonalAccount() {
        return personalAccount;
    }

    public String getInitialSaldo() {
        return initialSaldo;
    }

    public List<String> getDescription() {
        return description;
    }



    @Override
    public String toString() {
        return "ResultDto{" +
                "personalAccount='" + personalAccount + '\'' +
                ", initialSaldo='" + initialSaldo + '\'' +
                ", description=" + description +
                '}';
    }
}
