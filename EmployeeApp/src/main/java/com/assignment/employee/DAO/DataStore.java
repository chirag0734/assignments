package com.assignment.employee.DAO;

import com.assignment.employee.model.Employee;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chirjain on 12/23/2015.
 */
public class DataStore {
    public static Map<Integer,Employee> employees = new HashMap<Integer,Employee>();

    public static void save(Employee employee){
        /*employees.put(employee.getEmpId(), employee);*/
    }

    public static Employee get(int id){

        Employee emp = null;

        emp =  employees.get(id);

        return emp;
    }
}
