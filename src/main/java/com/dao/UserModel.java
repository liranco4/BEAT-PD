package com.dao;

import com.dm.Cow;
import com.dm.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by liran on 5/4/17.
 */

public class UserModel extends ModelGenerics {

    public String uploadUserToDB(User i_User){
        return uploadObjectToDB(i_User);
    }

    public String getUserByLoginName(String i_LoginName) {
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

    public static void main(String args[]){
        User us = new User("אחד","אחד","אחד","אחד");
        UserModel um = new UserModel();
        um.uploadUserToDB(us);
    }
}
