package FillingTables.services;

import FillingTables.dao.PersonalAccountDao;
import FillingTables.dto.ChargeDto;
import FillingTables.dto.PaymentDto;
import FillingTables.dto.ResultDto;
import FillingTables.dto.SaldoDto;
import FillingTables.model.PersonAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class PersonalAccountService {
    private final PersonalAccountDao personalAccountDao;

    @Autowired
    public PersonalAccountService(PersonalAccountDao personalAccountDao) {
        this.personalAccountDao = personalAccountDao;
    }


    /**
     * Метод, который сохраняет введённые данные с формы
     *
     * @param personAccount объект в котором содержится все введённые данные
     */
    public void save(PersonAccount personAccount) {
        // сначала сохраним начисления в БД
        ChargeDto chargeDto = new ChargeDto(personAccount.getId(), personAccount.getAllCharges());
        personalAccountDao.saveCharges(chargeDto);

        // сохраним платежи в БД
        PaymentDto paymentDto = new PaymentDto(personAccount.getId(), personAccount.getAllPayments());
        personalAccountDao.savePayments(paymentDto);

        // теперь нужно сформировать Map, где ключ - строка, а значение - сальдо
        Map<String, Double> saldo = createSaldo(personAccount.getAllCharges(), personAccount.getAllPayments(), personAccount.getInitialSaldo());
        SaldoDto saldoDto = new SaldoDto(personAccount.getId(), saldo);
        personalAccountDao.saveSaldo(saldoDto);
    }


    private Map<String, Double> createSaldo(Map<String, Double> charges, Map<String, Double> payments, double initialSaldo) {
        Map<String, Double> saldo = new HashMap<>();
        saldo.put("initial", initialSaldo);

        double temp = initialSaldo;

        for (Map.Entry<String, Double> entry : charges.entrySet()) {
            temp += entry.getValue();
            temp -= payments.get(entry.getKey());
            saldo.put(entry.getKey(), temp);
        }

        return saldo;
    }


    public List<ResultDto> createDtoList() throws SQLException {
        ResultSet charges = personalAccountDao.getCharges();
        Map<String, Map<String, Double>> chargeAccounts = createChargeAccounts(charges);

        ResultSet payments = personalAccountDao.getPayments();
        Map<String, Map<String, Double>> paymentsAccounts = createPaymentsAccounts(payments);

        ResultSet saldo = personalAccountDao.getSaldo();
        Map<String, Map<String, Double>> saldoAccounts = createSaldoAccounts(saldo);

        // теперь можно создать заключительный объект
        return createCommonResultDtoList(chargeAccounts, paymentsAccounts, saldoAccounts);

    }

    private List<ResultDto> createCommonResultDtoList(Map<String, Map<String, Double>> chargeAccounts ,  Map<String, Map<String, Double>> paymentsAccounts ,  Map<String, Map<String, Double>> saldoAccounts) {

        List<ResultDto> finalList = new LinkedList<>();
        for (String personalAccount : chargeAccounts.keySet()) {
            ResultDto resultDto = new ResultDto(personalAccount , chargeAccounts.get(personalAccount) , paymentsAccounts.get(personalAccount) , saldoAccounts.get(personalAccount));
            finalList.add(resultDto);
        }
        return finalList;
    }

    private Map<String, Map<String, Double>> createSaldoAccounts(ResultSet saldo) throws SQLException {
        Map<String, Map<String, Double>> saldoAccounts = new LinkedHashMap<>();
        while (saldo.next()) {
            String personalAccount = saldo.getString("personal_account");
            String month = saldo.getString("month");
            double value = saldo.getDouble("value");
            if (saldoAccounts.containsKey(personalAccount)) {
                Map<String, Double> temp = saldoAccounts.get(personalAccount);
                temp.put(month, value);
                saldoAccounts.put(personalAccount, temp);
            } else {
                Map<String, Double> temp = new LinkedHashMap<>();
                temp.put(month, value);
                saldoAccounts.put(personalAccount, temp);
            }
        }
        return saldoAccounts;
    }

    private Map<String, Map<String, Double>> createPaymentsAccounts(ResultSet payments) throws SQLException {
        Map<String, Map<String, Double>> paymentAccounts = new LinkedHashMap<>();
        while (payments.next()) {
            String personalAccount = payments.getString("personal_account");
            String month = payments.getString("month");
            double value = payments.getDouble("value");

            if (paymentAccounts.containsKey(personalAccount)) {
                Map<String, Double> temp = paymentAccounts.get(personalAccount);
                temp.put(month, value);
                paymentAccounts.put(personalAccount, temp);
            } else {
                Map<String, Double> temp = new LinkedHashMap<>();
                temp.put(month, value);
                paymentAccounts.put(personalAccount, temp);
            }
        }
        return paymentAccounts;
    }

    private Map<String, Map<String, Double>> createChargeAccounts(ResultSet charges) throws SQLException {
        Map<String, Map<String, Double>> chargeAccounts = new LinkedHashMap<>();
        while (charges.next()) {
            String personalAccount = charges.getString("personal_account");
            String month = charges.getString("month");
            double value = charges.getDouble("value");
            if (chargeAccounts.containsKey(personalAccount)) {
                Map<String, Double> temp = chargeAccounts.get(personalAccount);
                temp.put(month, value);
                chargeAccounts.put(personalAccount, temp);
            } else {
                Map<String, Double> temp = new LinkedHashMap<>();
                temp.put(month, value);
                chargeAccounts.put(personalAccount, temp);
            }
        }
        return chargeAccounts;
    }


}
