package com.delivery_admin.storeadmin.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PageController {
    @RequestMapping(path = {"", "/index"})
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/order")
    public ModelAndView order() {
        return new ModelAndView("order/order");
    }
}
