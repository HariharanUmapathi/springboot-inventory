package com.drivers.application.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class MVCTestController {
        @GetMapping("/add")
        public ModelAndView add_numbers(HttpServletRequest request,HttpServletResponse response){
             ModelAndView mv=new ModelAndView();
             Integer result=0;
            try {
                Integer i1=Integer.parseInt(request.getParameter("num1"));
                Integer i2=Integer.parseInt(request.getParameter("num2"));
                System.out.println(i1+i2);
            } catch(NumberFormatException e){
                Integer i1=0;
                Integer i2=0;
                result=i1+i2;
            }
            mv.setViewName("DriverList");
            mv.addObject("result", result);
            return mv;
        }
}
