package com.drivers.application.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class MVCTestController {
        @GetMapping("/add")
        public ModelAndView add_numbers(HttpServletRequest request,HttpServletResponse response){
            Integer i1=Integer.parseInt(request.getParameter("num1"));
            Integer i2=Integer.parseInt(request.getParameter("num2"));
            System.out.println(i1+i2);
            ModelAndView mv=new ModelAndView();
            mv.setViewName("DriverList");
            mv.addObject("result", i1+i2);
            return mv;
        }
}
