package FillingTables.services;

import FillingTables.dao.PersonalAccountDao;

import FillingTables.dto.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class PersonalAccountService {
    private final PersonalAccountDao personalAccountDao;

    public static final Map<String, String> months = new LinkedHashMap<>();

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
        personalAccountDao.saveSaldo(personalAccount, value, year, "inital");
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
        // сохраним начисление
        personalAccountDao.saveCharge(personalAccount, value, year, month);

        /**
         * Далее идёт код для изменения сальдо
         */
        // теперь нужно позабиться об создании сальдо
        // нужно к сальдо (n-1) месяца прибавить данное значение charge
        // нужно получить сальдо на (n-1) месяц
        // для этого нужно составить табличку(map)
        String previousMonth = months.get(month);


        // здесь хранится сальдо на предыдущий месяц (будем считать что месяца заполняются постепенно и за предыдущий месяц оно уже начислено)

        // нужно проверить сначала есть ли записанное сальдо уже
        // вдруг прошла выплата и сальдо какое-то уже записалось
        ResultSet currentSaldo = personalAccountDao.getSaldo(month, year, personalAccount);
        if (currentSaldo.next()) {
            // сюда мы зашли если у нас уже было записано сальдо и нам нужно его изменить

            // сохраним текущее сальдо
            double valueCurrentSaldo = currentSaldo.getDouble("value");

            // запишем новое сальдо
            double newSaldo = valueCurrentSaldo + value;

            // запишем новое сальдо
            personalAccountDao.changeSaldo(personalAccount, month, year, newSaldo);
        } else {
            // иначе сальдо ещё не записывалось, нужно найти сальдо на прошлый месяц
            ResultSet previosSaldo = personalAccountDao.getSaldo(previousMonth, year, personalAccount);
            previosSaldo.next();

            // сальдо на прошлый месяц
            double valuePreviousSaldo = previosSaldo.getDouble("value");

            // новое сальдо на текущий месяц
            double newSaldo = valuePreviousSaldo + value;

            // сохраняем новое сальдо
            personalAccountDao.saveSaldo(personalAccount, newSaldo, year, month);
        }

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
        // сохраним платёж
        personalAccountDao.savePayment(personalAccount, value, year, month);
        /**
         * Далее идёт код для изменения сальдо
         */
        // теперь нужно позабиться об создании сальдо
        // нужно к сальдо (n-1) месяца прибавить данное значение charge
        // нужно получить сальдо на (n-1) месяц
        // для этого нужно составить табличку(map)
        String previousMonth = months.get(month);


        // здесь хранится сальдо на предыдущий месяц (будем считать что месяца заполняются постепенно и за предыдущий месяц оно уже начислено)

        // нужно проверить сначала есть ли записанное сальдо уже
        // вдруг прошла выплата и сальдо какое-то уже записалось
        ResultSet currentSaldo = personalAccountDao.getSaldo(month, year, personalAccount);
        if (currentSaldo.next()) {
            // сюда мы зашли если у нас уже было записано сальдо и нам нужно его изменить

            // сохраним текущее сальдо
            double valueCurrentSaldo = currentSaldo.getDouble("value");

            // запишем новое сальдо
            double newSaldo = valueCurrentSaldo - value;

            // запишем новое сальдо
            personalAccountDao.changeSaldo(personalAccount, month, year, newSaldo);
        } else {
            // иначе сальдо ещё не записывалось, нужно найти сальдо на прошлый месяц
            ResultSet previosSaldo = personalAccountDao.getSaldo(previousMonth, year, personalAccount);
            previosSaldo.next();

            // сальдо на прошлый месяц
            double valuePreviousSaldo = previosSaldo.getDouble("value");

            // новое сальдо на текущий месяц
            double newSaldo = valuePreviousSaldo - value;

            // сохраняем новое сальдо
            personalAccountDao.saveSaldo(personalAccount, newSaldo, year, month);
        }

    }
    
    public List<ResultDto> createResulList(String year) throws SQLException {
        // нам нужно получить по каждому из лицевого счёта -
        // 1 - все сальдо на месяцы данного года
        // 2 - все начисления на месяца данного года
        // 3 - все платежи на месяц данного года

        // но для начала хотелось бы получить все уникальные номера лицевых счётов
        List<String> allPersonalAccount = new ArrayList<>();
        ResultSet personalAccount = personalAccountDao.getAllUniquePersonalAccount();
        while (personalAccount.next()) {
            allPersonalAccount.add(personalAccount.getString("personal_account"));
        }
        System.out.println(allPersonalAccount);

        // создаём структуры данных для хранения всех выплат и платежей и сальдо
        // здесь ключ - лицевой счёт
        Map<String, LinkedHashMap<String, Double>> charges = new HashMap<>();
        Map<String, LinkedHashMap<String, List<Double>>> payments = new HashMap<>();
        Map<String, LinkedHashMap<String, Double>> saldo = new HashMap<>();

        // инициализируем начальными значениями
        for (String name : allPersonalAccount) {
            LinkedHashMap<String, Double> tempForCharge = new LinkedHashMap<>();
            LinkedHashMap<String, List<Double>> tempForPayment = new LinkedHashMap<>();
            LinkedHashMap<String, Double> tempForSaldo = new LinkedHashMap<>();
            tempForSaldo.put("initial", (double) 0);
            for (String currentName : months.keySet()) {
                tempForCharge.put(currentName, (double) 0);
                tempForPayment.put(currentName, new ArrayList<>());
                tempForSaldo.put(currentName, (double) 0);
            }
            charges.put(name, tempForCharge);
            payments.put(name, tempForPayment);
            saldo.put(name, tempForSaldo);
        }

        // теперь нужно получить все начисления
        ResultSet chargeFromDB = personalAccountDao.getCharge(year);

        // теперь прочитаем из resultSet все значения в Map
        while (chargeFromDB.next()) {
            // здесь происходит заполнение map charges
            // текущий id
            String id = chargeFromDB.getString("personal_account");
            // текущий месяц
            String month = chargeFromDB.getString("month");
            // текущее значение
            Double value = chargeFromDB.getDouble("value");
            // забираем саму map по данному ключу
            LinkedHashMap<String, Double> currentValues = charges.get(id);
            // добавляем значение для текущего месяца
            currentValues.put(month, value);
            // кладём обратно
            charges.put(id, currentValues);
        }

        // теперь посмотрим все платежи
        ResultSet paymentFromDB = personalAccountDao.getPayment(year);
        while (paymentFromDB.next()) {
            // здесь происходит заполнение map payments
            // текущий id
            String id = paymentFromDB.getString("personal_account");
            // текущий месяц
            String month = paymentFromDB.getString("month");
            // текущее значение
            Double value = paymentFromDB.getDouble("value");
            // нужно достать map из payment
            LinkedHashMap<String, List<Double>> currentValues = payments.get(id);
            // нужно достать текущий лист платежей
            List<Double> currentMonthList = currentValues.get(month);
            // добавим платёж
            currentMonthList.add(value);
            // положим в map текущее значение листа для платежей
            currentValues.put(month, currentMonthList);
            // сохраним в главной map изменившиеся значения
            payments.put(id, currentValues);
        }

        // теперь нужно записать все сальдо
        ResultSet saldoFromDB = personalAccountDao.getSaldo(year);
        while (saldoFromDB.next()) {
            // здесь происходит заполнение map charges
            // текущий id
            String id = saldoFromDB.getString("personal_account");
            // текущий месяц
            String month = saldoFromDB.getString("month");
            // текущее значение
            Double value = saldoFromDB.getDouble("value");
            // забираем саму map по данному ключу
            LinkedHashMap<String, Double> currentValues = saldo.get(id);
            // добавляем значение для текущего месяца
            currentValues.put(month, value);
            // кладём обратно
            saldo.put(id, currentValues);
        }


        // теперь нужно проверить - есть ли пустые List в платежах, вдруг там не было платежей
        // поэтому нужно их самим поставить значение 0
        for (Map.Entry<String, LinkedHashMap<String, List<Double>>> entry : payments.entrySet()) {
            LinkedHashMap<String, List<Double>> temp = entry.getValue();
            for (Map.Entry<String, List<Double>> listEntry : temp.entrySet()) {
                List<Double> currentPaymentsForMonth = listEntry.getValue();
                if (currentPaymentsForMonth.isEmpty()) {
                    currentPaymentsForMonth.add((double) 0);
                    temp.put(listEntry.getKey(), currentPaymentsForMonth);
                }
            }
        }
        List<ResultDto> resultDtoList = new ArrayList<>();
        // а теперь можно создавать объекты ResultDto, которые мы будем отправлять в html форму
        for (Map.Entry<String, LinkedHashMap<String, Double>> saldoEntrySet : saldo.entrySet()) {
            String id = saldoEntrySet.getKey();
            resultDtoList.add(new ResultDto(id, charges.get(id), payments.get(id), saldoEntrySet.getValue()));
        }

        return resultDtoList;
    }


}
