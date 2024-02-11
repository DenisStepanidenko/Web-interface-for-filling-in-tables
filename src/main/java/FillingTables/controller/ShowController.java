package FillingTables.controller;

import FillingTables.dto.ResultDto;
import FillingTables.services.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ShowController {
    private final PersonalAccountService personalAccountService;

    @Autowired
    public ShowController(PersonalAccountService personalAccountService) {
        this.personalAccountService = personalAccountService;
    }


    @GetMapping("/show")
    public String show(Model model) throws SQLException {
        // нам нужен лист из следующего объекта:
        // personal_account
        // saldo на месяц n
        // начисления на месяц n
        // платежи на месяц n
        // personal_account
        List<ResultDto> dtoList = personalAccountService.createDtoList();
        System.out.println(dtoList.get(0));
        model.addAttribute("dtoList" , dtoList);
        return "personalAccount/show";
    }
}
