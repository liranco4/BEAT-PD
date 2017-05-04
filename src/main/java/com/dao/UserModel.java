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

public class UserModel implements Model{

    public static String uploadUser(User i_User){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(i_User);
            transaction.commit();
            session.close();
            return String.format("{success:%s}", i_User);
        }catch (Exception e){
            return String.format("{error:%s}", e.getMessage());
        }
    }

    public static String getUserByLoginName(String i_LoginName) {
        try {
            Session session = sessionFactory.openSession();
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
