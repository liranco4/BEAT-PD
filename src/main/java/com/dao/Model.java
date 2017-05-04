package com.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by liran on 5/4/17.
 */
public interface Model {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
}
