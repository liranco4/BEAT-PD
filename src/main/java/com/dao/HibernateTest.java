//package com.dao;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//
//
///**
// * Created by liran cohen on 27/04/17.
// */
//public class HibernateTest {
//    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//
////   public static void main(String args[]) {
////       Cow cow = new Cow();
////       cow.setCowID(3);
////       cow.setCowName("LALALA");
////       uploadCow(cow);
////   }
//////
//////       Session session = sessionFactory.openSession();
////////       session.beginTransaction();
////////       session.save(cow);
////////       session.getTransaction().commit();
////////       session.close();
//////
//////       session = sessionFactory.openSession();
//////       session.beginTransaction();
//////
//////      Cow cow1 = session.get(Cow.class,2);
//////      session.close();
//////      System.out.println("retrieving cow from database: " + cow1.toString());
//////      sessionFactory.close();
////   }
////   public static Cow getCowByID2(){
////       Session session = sessionFactory.openSession();
////       session.beginTransaction();
////       Cow cow1 = session.get(Cow.class,2);
////       session.close();
////       return cow1;
////   }
////    public static String getCowByID(int id){
////        Session session = sessionFactory.openSession();
////        session.beginTransaction();
////        Cow cow1 = session.get(Cow.class,id);
////        session.close();
////        return cow1.toString();
////    }
////
////    public static String uploadCow(Cow i_Cow){
////        Session session = sessionFactory.openSession();
////        Transaction tx = session.beginTransaction();
////        session.save(i_Cow);
////        tx.commit();
////        session.close();
////        return String.format("{success:%s}",i_Cow);
////    }
//}
