package FillingTables.controller;

import FillingTables.dto.ResultDto;
import FillingTables.services.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ShowController {
    private final PersonalAccountService personalAccountService;

    @Autowired
    public ShowController(PersonalAccountService personalAccountService) {
        this.personalAccountService = personalAccountService;
    }

    @PostMapping("/show")
    public String showTable(@RequestParam("year") String year, Model model){

        List<ResultDto> resultDtoList = personalAccountService.createResulList(year);

        return "redirect:/home";
    }
}
