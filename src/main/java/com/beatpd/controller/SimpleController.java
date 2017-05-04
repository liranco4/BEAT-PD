package com.beatpd.controller;


import com.dao.HibernateTest;
import com.dm.Cow;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/BEAT-PD/cow")
public class SimpleController {
//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public String hello() {
//
//		return "You Start here";
//	}

    @RequestMapping(value = "/2", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String getCowByID() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(HibernateTest.getCowByID2());
            return json;
        } catch (Exception e) {
            return String.format("{error:%s}",e.getMessage());
        }

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String someMethod(int value) {
        //do stuff with valueOne variable her
        return HibernateTest.getCowByID(value);
    }

    @RequestMapping(value = "/uploadCow", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String postWithJson(@RequestBody String json) {
        //do stuff with valueOne variable her
        ObjectMapper mapper = new ObjectMapper();
        try {
            Cow pj = mapper.readValue(json, Cow.class);
            return mapper.writeValueAsString(pj);
        } catch (Exception e) {
            return String.format("{error:%s}",e.getMessage());
        }
    }
}