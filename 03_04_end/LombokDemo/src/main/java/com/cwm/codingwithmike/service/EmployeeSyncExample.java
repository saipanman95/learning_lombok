package com.cwm.codingwithmike.service;

import lombok.Synchronized;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EmployeeSyncExample {
    private final Object myLock = new Object();
    private static final Map<String, String> employeeMap = new HashMap<>();
    
    @Synchronized
    public static void printEmploye(){
        final Set<String> keys = employeeMap.keySet();
        for(String key : keys){
            System.out.println(employeeMap.get(key));
        }
    }

    @Synchronized
    public void addEmployee(String id, String empName){
        employeeMap.put(id, empName);
    }

    @Synchronized("myLock")
    public void updateEmployee(String id, String empName){
        employeeMap.replace(id, empName);
    }
}
