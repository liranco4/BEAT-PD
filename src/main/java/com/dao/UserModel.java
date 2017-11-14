package com.dao;

import com.dm.User;

import java.util.Date;

import static java.lang.String.format;

/**
 * Created by liran on 5/4/17.
 */

public class UserModel extends ModelGenerics {

    private UserModel() {
    }

    private static UserModel userModelInstance;
    private ModelGenerics modelGenerics = getModelGenericsInstance();

    public static UserModel getUserModelInstance() {
        if (userModelInstance == null)
            userModelInstance = new UserModel();
        return userModelInstance;
    }

    public Boolean checkCredentials(User user) {
        User userFromDB = (User) modelGenerics.retrieveObjectFromDBbyID(User.class, user.getID());
        if(userFromDB.getPass().equals(user.getPass()) && "Admin".equals(userFromDB.getRole())){
            userFromDB.setLastLogin(new Date());
            modelGenerics.updateObjectToDB(userFromDB);
            return true;
        }
        return false;
    }
}