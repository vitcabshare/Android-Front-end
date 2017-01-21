package com.example.tanmayjha.vitcabs.Control.Constants;

/**
 * Created by tanmay on 21/1/17.
 */

public class AccountInformation {
    static String lastName,phoneNo,email;

    public AccountInformation(String lastName,String phoneNo,String email) {
        this.lastName=lastName;
        this.phoneNo=phoneNo;
        this.email=email;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getPhoneNo() {
        return phoneNo;
    }

    public static String getEmail() {
        return email;
    }

    public static void setLastName(String lastName) {

        AccountInformation.lastName = lastName;
    }

    public static void setPhoneNo(String phoneNo) {
        AccountInformation.phoneNo = phoneNo;
    }

    public static void setEmail(String email) {
        AccountInformation.email = email;
    }
}
