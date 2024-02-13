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

    public static final Map<String, String> months = new HashMap<>();

    static {
        months.put("January", "inital");
        months.put("February", "January");
        months.put("March", "February");
        months.put("April", "March");
        months.put("May", "April");
        months.put("June", "May");
        months.put("July", "June");
        months.put("August", "July");
        months.put("September", "August");
        months.put("October", "September");
        months.put("November", "October");
        months.put("December", "November");
    }

    @Autowired
    public PersonalAccountService(PersonalAccountDao personalAccountDao) {
        this.personalAccountDao = personalAccountDao;
    }


    /**
     * Метод, который вызывает DAO, который сохраняет значение изначального сальдо
     *
     * @param personalAccount лицевой счёт
     * @param value           значение сальдо
     * @param year            год
     */
    public void saveSaldo(String personalAccount, double value, String year) throws SQLException {
        personalAccountDao.saveSaldo(personalAccount, value, year);
    }


    /**
     * Метод, который вызывает DAO, который сохраняет значение для текущего начисления
     *
     * @param personalAccount лицевой счёт
     * @param value           значение начисления
     * @param year            год
     * @param month           месяц
     */
    public void saveCharge(String personalAccount, double value, String year, String month) throws SQLException {
        personalAccountDao.saveCharge(personalAccount, value, year, month);

        /**
         * Далее идёт код для сохранения сальдо, так как для данного
         */
        // теперь нужно позабиться об создании сальдо
        // нужно к сальдо (n-1) месяца прибавить данное значение charge
        // нужно получить сальдо на (n-1) месяц
        // для этого нужно составить табличку(map)
        String previousMonth = months.get(month);


        // здесь хранится сальдо на предыдущий месяц
        ResultSet resultSet = personalAccountDao.getPreviousSaldo(previousMonth, year, personalAccount);
        resultSet.next();
        double valuePreviousSaldo = resultSet.getDouble("value");

        double newSaldo = valuePreviousSaldo + value; // здесь хранится новое сальдо


        // теперь нужно изменить сальдо
        personalAccountDao.changeSaldo(personalAccount, month, year, newSaldo);
    }


    /**
     * Метод, который записывает платеж
     *
     * @param personalAccount лицевой счёт
     * @param value           значение
     * @param year            год
     * @param month           месяц
     */
    public void savePayment(String personalAccount, double value, String year, String month) throws SQLException {
        personalAccountDao.savePayment(personalAccount, value, year, month);
    }

    public List<ResultDto> createResulList(String year) {
        // нам нужно получить по каждому из лицевого счёта -
        // 1 - все сальдо на месяцы данного года
        // 2 - все начисления на месяца данного года
        // 3 - все платежи на месяц данного года

        // но для начала хотелось бы получить все уникальные номера лицевых счётов
        Map<String, LinkedHashMap<String, Double>> allCharges = new HashMap<>();
        return null;
    }


}
