package com.beatpd.controller;

import com.dao.HibernateTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/BEAT-PD/cow")
public class SimpleController 
{
//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public String hello() {
//
//		return "You Start here";
//	}
    @RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String getCowByID() {
		return HibernateTest.getCowByID2();
	}
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public String someMethod(int value) {
        //do stuff with valueOne variable her
       return HibernateTest.getCowByID(value).toString();
    }
}
