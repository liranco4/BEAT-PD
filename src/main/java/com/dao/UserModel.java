package com.dao;

import com.dm.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by liran on 5/4/17.
 */

public class UserModel extends ModelGenerics {

    private UserModel(){}

    private static UserModel userModelInstance;
    private ModelGenerics modelGenerics = getModelGenericsInstance();
    public static UserModel getUserModelInstance(){
        if(userModelInstance == null)
            userModelInstance = new UserModel();
        return userModelInstance;
    }

    public Boolean checkCredentials(User user) {
        User userFromDB = (User)modelGenerics.retrieveObjectFromDBbyID(User.class, user.getUserID());
        return (userFromDB.getUserPassword().equals(user.getUserPassword()) && user.getUserRole().equals(userFromDB.getUserRole()));
    }
}
