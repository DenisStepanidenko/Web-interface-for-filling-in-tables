package FillingTables.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;

@Controller
public class HomeController {


    /**
     * Метод, который показывает главную страницу
     * @return возвращает html документ
     * @throws SQLException исключение, которое генерируется SQL
     */
    @GetMapping("/home")
    public String test() throws SQLException {
        return "personalAccount/home";
    }
}
