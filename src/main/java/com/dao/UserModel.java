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

    public static UserModel getUserModelInstance(){
        if(userModelInstance == null)
            userModelInstance = new UserModel();
        return userModelInstance;
    }

    public String getUserByUserLoginName(String i_LoginName) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, i_LoginName);
            transaction.commit();
            session.close();
            return user.toString();
        } catch (Exception e) {
            return String.format("{error:%s}", e.getMessage());
        }
    }
}
