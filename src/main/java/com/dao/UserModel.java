package com.dao;

import com.dm.Activity;
import com.dm.Patient;
import com.dm.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by liran on 5/4/17.
 */

public class UserModel extends ModelGenerics {

    public String addUserToDB(User i_User){
        return addObjectToDB(i_User);
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
    public String updateUserToDB(User i_Patient){

        return updateObjectToDB(i_Patient);
    }
}
