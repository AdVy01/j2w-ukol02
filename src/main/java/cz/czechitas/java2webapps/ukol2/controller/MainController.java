package cz.czechitas.java2webapps.ukol2.controller;

import cz.czechitas.java2webapps.ukol2.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private final QuotationService quotationService;

    @Autowired
    public MainController(QuotationService service) {
        quotationService = service;
    }

    @GetMapping("/")
    public ModelAndView quotations() {
        ModelAndView result = new ModelAndView("citaty");
        result.addObject("imageUrl", quotationService.getImageUrl());
        result.addObject("quotation", quotationService.randomQuotation());
        return result;
    }

}
