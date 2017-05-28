package com.dao;

import com.dm.Activity;
import com.dm.Medicine;
import com.dm.Patient;
import com.fasterxml.jackson.core.JsonParser;
import com.utils.CustomDate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by liran on 5/11/17.
 */
public class PatientModel extends ModelGenerics {
    public String addPatientToDB(Patient i_Patient) {
        return addObjectToDB(i_Patient);
    }
}
