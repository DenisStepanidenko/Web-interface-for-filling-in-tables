package FillingTables.dao;

import FillingTables.dto.ChargeDto;
import FillingTables.dto.PaymentDto;
import FillingTables.dto.SaldoDto;
import org.springframework.stereotype.Component;

import java.sql.*;

import java.util.Map;

@Component
public class PersonalAccountDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "123";
    private static final Connection connection;
    public static final String SQL_FOR_SAVE_CHARGE = "INSERT INTO charges(personal_account, year, month, value) VALUES(?,?,?,?)";
    public static final String SQL_FOR_SAVE_PAYMENTS = "INSERT INTO payments(personal_account, year, month, value) VALUES(?,?,?,?)";
    public static final String SQL_FOR_SAVE_SALDO = "INSERT INTO saldo(personal_account, year, month, value) VALUES(?,?,?,?)";
    public static final String SQL_FOR_GETTING_CHARGES = "SELECT * FROM charges ";
    public static final String SQL_FOR_GETTING_PAYMENTS = "SELECT * FROM payments";
    public static final String SQL_FOR_GETTING_SALDO = "SELECT * FROM saldo";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void saveCharges(ChargeDto chargeDto) {
//        // в charge dto у нас есть id(personal_account) и значения начислений по месяцам
//
//        for (Map.Entry<String, Double> entry : chargeDto.getAllCharges().entrySet()) {
//            try {
//                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_SAVE_CHARGES);
//                preparedStatement.setString(1, String.valueOf(chargeDto.getId()));
//                preparedStatement.setString(2, "2024");
//                preparedStatement.setString(3, entry.getKey());
//                preparedStatement.setDouble(4, entry.getValue());
//                preparedStatement.executeUpdate();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }

//    public void savePayments(PaymentDto paymentDto) {
//        for (Map.Entry<String, Double> entry : paymentDto.getPayments().entrySet()) {
//            try {
//                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_SAVE_PAYMENTS);
//                preparedStatement.setString(1, String.valueOf(paymentDto.getId()));
//                preparedStatement.setString(2, "2024");
//                preparedStatement.setString(3, entry.getKey());
//                preparedStatement.setDouble(4, entry.getValue());
//                preparedStatement.executeUpdate();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }


    /**
     * Метод, который записывает в БД значение для изначального сальдо
     *
     * @param personalAccount лицевой счёт
     * @param value           значение
     * @param year            год
     */
    public void saveSaldo(String personalAccount, double value, String year) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_SAVE_SALDO);
        preparedStatement.setString(1, personalAccount);
        preparedStatement.setString(2, year);
        preparedStatement.setString(3, "inital");
        preparedStatement.setDouble(4, value);
        preparedStatement.executeUpdate();
    }


    /**
     * Метод, который записывает значение для начисления
     * @param personalAccount лицевой счёт
     * @param value значение
     * @param year год
     * @param month месяц
     */
    public void saveCharge(String personalAccount, double value, String year, String month) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_SAVE_CHARGE);
        preparedStatement.setString(1, personalAccount);
        preparedStatement.setString(2, year);
        preparedStatement.setString(3,  month);
        preparedStatement.setDouble(4, value);
        preparedStatement.executeUpdate();
    }


//    public ResultSet getCharges() {
//        try {
//            Statement statement = connection.createStatement();
//            return statement.executeQuery(SQL_FOR_GETTING_CHARGES);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public ResultSet getPayments() {
//        try {
//            Statement statement = connection.createStatement();
//            return statement.executeQuery(SQL_FOR_GETTING_PAYMENTS);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public ResultSet getSaldo() {
//        try {
//            Statement statement = connection.createStatement();
//            return statement.executeQuery(SQL_FOR_GETTING_SALDO);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
