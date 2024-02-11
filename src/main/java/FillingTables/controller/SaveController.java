package FillingTables.controller;

import FillingTables.model.PersonAccount;
import FillingTables.services.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class SaveController {
    private final PersonalAccountService personalAccountService;

    @Autowired
    public SaveController(PersonalAccountService personalAccountService) {
        this.personalAccountService = personalAccountService;
    }


    @GetMapping("/new")
    public String newPersonalAccount(Model model){
        PersonAccount personAccount = new PersonAccount();
        model.addAttribute("account" , personAccount);
        return "personalAccount/new";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute("account") PersonAccount personAccount){
        personalAccountService.save(personAccount);
        return "redirect:/home";
    }

}
