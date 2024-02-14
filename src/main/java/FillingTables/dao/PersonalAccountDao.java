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
    public static final String SQL_FOR_SAVE_PAYMENT = "INSERT INTO payments(personal_account, year, month, value) VALUES(?,?,?,?)";
    public static final String SQL_FOR_SAVE_SALDO = "INSERT INTO saldo(personal_account, year, month, value) VALUES(?,?,?,?)";
    public static final String SQL_FOR_GETTING_SALDO_ON_PREVIOUS_MONTH = "SELECT value FROM saldo WHERE month = ? and year = ? and personal_account = ?";
    public static final String SQL_FOR_CHANGE_SALDO = "UPDATE saldo set value = ? where personal_account = ? and year = ? and month = ?";
    public static final String SQL_FOR_GETTING_ALL_UNIQUE_ID = "SELECT DISTINCT saldo.personal_account FROM saldo";
    public static final String SQL_FOR_GETTING_ALL_CHARGE_BY_YEAR = "SELECT * FROM charges where year = ?";
    public static final String SQL_FOR_GETTING_ALL_PAYMENTS_BY_YEAR = "SELECT * FROM payments where year = ?";

    public static final String SQL_FOR_GETTING_ALL_SALDO_BY_YEAR = "SELECT * FROM saldo where year = ?";


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

    /**
     * Метод, который записывает в БД значение для изначального сальдо
     *
     * @param personalAccount лицевой счёт
     * @param value           значение
     * @param year            год
     */
    public void saveSaldo(String personalAccount, double value, String year, String month) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_SAVE_SALDO);
        preparedStatement.setString(1, personalAccount);
        preparedStatement.setString(2, year);
        preparedStatement.setString(3, month);
        preparedStatement.setDouble(4, value);
        preparedStatement.executeUpdate();
    }


    /**
     * Метод, который записывает значение для начисления
     *
     * @param personalAccount лицевой счёт
     * @param value           значение
     * @param year            год
     * @param month           месяц
     */
    public void saveCharge(String personalAccount, double value, String year, String month) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_SAVE_CHARGE);
        preparedStatement.setString(1, personalAccount);
        preparedStatement.setString(2, year);
        preparedStatement.setString(3, month);
        preparedStatement.setDouble(4, value);
        preparedStatement.executeUpdate();
    }


    /**
     * Метод, который записывает значение для платежа
     *
     * @param personalAccount лицевой счёт
     * @param value           значение
     * @param year            год
     * @param month           месяц
     */
    public void savePayment(String personalAccount, double value, String year, String month) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_SAVE_PAYMENT);
        preparedStatement.setString(1, personalAccount);
        preparedStatement.setString(2, year);
        preparedStatement.setString(3, month);
        preparedStatement.setDouble(4, value);
        preparedStatement.executeUpdate();
    }


    /**
     * Метод, который получает значение сальдо по месяцу,году и лицевому счёту
     *
     * @param month           месяц
     * @param year            год
     * @param personalAccount лицевой счёт
     * @return resultSet
     */
    public ResultSet getSaldo(String month, String year, String personalAccount) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_SALDO_ON_PREVIOUS_MONTH);
        preparedStatement.setString(1, month);
        preparedStatement.setString(2, year);
        preparedStatement.setString(3, personalAccount);
        return preparedStatement.executeQuery();
    }

    public ResultSet getCharge(String year) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_ALL_CHARGE_BY_YEAR);
            preparedStatement.setString(1, year);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getPayment(String year) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_ALL_PAYMENTS_BY_YEAR);
            preparedStatement.setString(1, year);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet getSaldo(String year) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_ALL_SALDO_BY_YEAR);
            preparedStatement.setString(1, year);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Обновляет значение сальдо по году, месяц, лицевому счёту
     *
     * @param personalAccount лицевой счёт
     * @param month           месяц
     * @param year            год
     * @param newSaldo        новое значение
     */
    public void changeSaldo(String personalAccount, String month, String year, double newSaldo) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_CHANGE_SALDO);
        preparedStatement.setDouble(1, newSaldo);
        preparedStatement.setString(2, personalAccount);
        preparedStatement.setString(3, year);
        preparedStatement.setString(4, month);
        preparedStatement.executeUpdate();
    }

    public ResultSet getAllUniquePersonalAccount() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_ALL_UNIQUE_ID);
        return preparedStatement.executeQuery();
    }
}
