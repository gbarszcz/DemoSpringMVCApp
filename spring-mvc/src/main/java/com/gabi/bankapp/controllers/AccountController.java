package com.gabi.bankapp.controllers;

import com.gabi.bankapp.model.Account;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor ste = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, ste);
    }

    @RequestMapping(value = {"", "/"})
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/newAccount")
    public String newAccount(Model model) {
        model.addAttribute("account", new Account());
        return "newAccount";
    }

    @RequestMapping("/showAccount")
    public String showAccount() {
        return "showAccount";
    }

    // Commented out due to a simplified version below
/*    @RequestMapping(value="/saveAccount", method = RequestMethod.POST)
    public String saveAccount(Model model,
                              @RequestParam("accountNumber") String accountNumber,
                              @RequestParam("accountHolderName") String accountHolderName,
                              @RequestParam("accountBalance") String accountBalance) {
        Account account = new Account(Integer.parseInt(accountNumber), accountHolderName, Integer.parseInt(accountBalance));
        model.addAttribute("account", account);
        return "showAccount";
    }*/

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String saveAccount(@Valid Account account, BindingResult result) {
        // Commented out due to using validations
        /*model.addAttribute("account", account);
        return "showAccount";*/

        if (result.hasErrors()) {
            return "newAccount";
        } else {
            return "showAccount";
        }
    }
}