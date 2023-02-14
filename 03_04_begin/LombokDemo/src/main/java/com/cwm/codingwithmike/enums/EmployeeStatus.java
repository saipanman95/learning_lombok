package com.cwm.codingwithmike.enums;

public enum EmployeeStatus {
    PARTTIME, FULLTIME, SELFEMPLOYED, UNKNOWN;

    public static EmployeeStatus convert(String status){
        for(EmployeeStatus employeeStatus : EmployeeStatus.values()){
            if(employeeStatus.toString().equalsIgnoreCase(status)){
                return employeeStatus;
            }
        }
        return EmployeeStatus.UNKNOWN;
    }
}
