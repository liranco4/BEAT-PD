package com.beatpd.controller;
import com.dao.ModelGenerics;
import com.dao.UserModel;
import com.dm.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liran on 5/4/17.
 */

@Controller
@RequestMapping("/BEAT-PD/Admin/")
public class AdminController {
    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private ObjectMapper objectMapper = new ObjectMapper();
    private ModelGenerics modelGenerics = new ModelGenerics();
    private UserModel userModel = new UserModel();

    @RequestMapping(value = "/Upload/User", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String uploadUser(@RequestBody String json) {

        try {
            User user = objectMapper.readValue(json, User.class);
            return modelGenerics.uploadObjectToDB(user);
        } catch (Exception e) {
            return String.format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/GET/User?value=", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String getUserByID(String value) {
        return userModel.getUserByLoginName(value);
    }
}
