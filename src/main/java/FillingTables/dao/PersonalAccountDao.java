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
    public static final String SQL_FOR_CHANGE_SALDO = "INSERT INTO saldo(personal_account, year, month, value) values (?,?,?,?)";

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


    public ResultSet getPreviousSaldo(String previousMonth, String year, String personalAccount) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_SALDO_ON_PREVIOUS_MONTH);
        preparedStatement.setString(1, previousMonth);
        preparedStatement.setString(2, year);
        preparedStatement.setString(3, personalAccount);
        return preparedStatement.executeQuery();
    }

    public void changeSaldo(String personalAccount, String month, String year, double newSaldo) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_CHANGE_SALDO);
        preparedStatement.setString(1, personalAccount);
        preparedStatement.setString(2, year);
        preparedStatement.setString(3, month);
        preparedStatement.setDouble(4, newSaldo);
        preparedStatement.executeUpdate();
    }
}
