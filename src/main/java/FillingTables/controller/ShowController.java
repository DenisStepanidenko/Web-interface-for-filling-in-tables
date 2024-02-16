package FillingTables.controller;

import FillingTables.dao.PersonalAccountDao;
import FillingTables.dto.ResultDto;
import FillingTables.services.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShowController {
    private final PersonalAccountService personalAccountService;

    @Autowired
    public ShowController(PersonalAccountService personalAccountService) {
        this.personalAccountService = personalAccountService;
    }


    /**
     * Контроллер, который отправляет html представление с выводом обратной ведомости
     *
     * @param year  текущий год
     * @param model модель, где будет храниться вся информация
     * @return html представление
     */
    @PostMapping("/show")
    public String showTable(@RequestParam("year") String year, Model model) throws SQLException {
        List<ResultDto> resultDtoList = personalAccountService.createResulList(year);

        model.addAttribute("dtoList", resultDtoList);
        return "personalAccount/show";
    }

    @GetMapping("/test")
    public String test(Model model) {

        BigDecimal newSaldo = BigDecimal.valueOf(4343.543 + 312453.5343);
        System.out.println(Double.parseDouble(String.valueOf(newSaldo)));
        return "redirect:/home";
    }
}
