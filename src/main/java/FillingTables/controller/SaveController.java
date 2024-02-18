package FillingTables.controller;


import FillingTables.services.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@Controller
public class SaveController {
    private final PersonalAccountService personalAccountService;

    @Autowired
    public SaveController(PersonalAccountService personalAccountService) {
        this.personalAccountService = personalAccountService;
    }

    /**
     * Контроллер, которые перехватывает данные для сальдо
     *
     * @param personalAccount номер лицевого счёта
     * @param value           значение сальдо
     * @param year            год для которого пишется сальдо
     * @return возвращает редирект на главную страницу
     */
    @PostMapping("/saldo")
    public String saveSaldo(@RequestParam("personal_account") String personalAccount, @RequestParam("value") double value, @RequestParam("year") String year) throws SQLException {
        personalAccountService.saveSaldo(personalAccount, value, year);
        return "redirect:/home";
    }


    /**
     * Контроллер, которые перехватывает данные для зачисления
     *
     * @param personalAccount номер лицевого счёта
     * @param value           значение начисления
     * @param year            год зачисления
     * @param month           месяц зачисления
     * @return возвращает редирект на главную страницу
     */
    @PostMapping("/charge")
    public String saveCharge(@RequestParam("personal_account") String personalAccount, @RequestParam("value") double value, @RequestParam("year") String year, @RequestParam("month") String month) throws SQLException {
        personalAccountService.saveCharge(personalAccount, value, year, month);
        return "redirect:/home";
    }


    /**
     * Контроллер, которые перехватывает данные для платежа
     *
     * @param personalAccount номер лицевого счёта
     * @param value           значение начисления
     * @param year            год зачисления
     * @param month           месяц зачисления
     * @return возвращает редирект на главную страницу
     */
    @PostMapping("/payment")
    public String savePayment(@RequestParam("personal_account") String personalAccount, @RequestParam("value") double value, @RequestParam("year") String year, @RequestParam("month") String month) throws SQLException {
        personalAccountService.savePayment(personalAccount, value, year, month);
        return "redirect:/home";
    }


}
