package com.dpiotr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dpiotr on 20.11.17.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mw = new ModelAndView();
        mw.setViewName("index");
        return mw;
    }
}

