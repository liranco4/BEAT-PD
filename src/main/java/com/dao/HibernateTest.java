package com.dao;

import com.dm.Cow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by liran on 27/04/17.
 */
public class HibernateTest {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//   public static void main(String args[]){
//       Cow cow = new Cow();
////       cow.setCowID(3);
////       cow.setCowName("LALALA");
//
////
////       Session session = sessionFactory.openSession();
//////       session.beginTransaction();
//////       session.save(cow);
//////       session.getTransaction().commit();
//////       session.close();
////
////       session = sessionFactory.openSession();
////       session.beginTransaction();
////
////      Cow cow1 = session.get(Cow.class,2);
////      session.close();
////      System.out.println("retrieving cow from database: " + cow1.toString());
////      sessionFactory.close();
//   }
   public static String getCowByID2(){
       Session session = sessionFactory.openSession();
       session.beginTransaction();
       Cow cow1 = session.get(Cow.class,2);
       return cow1.toString();
   }

    public static String getCowByID(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Cow cow1 = session.get(Cow.class,id);
        return cow1.toString();
    }
}
