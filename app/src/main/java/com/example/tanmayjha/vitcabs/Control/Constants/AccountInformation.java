package com.example.tanmayjha.vitcabs.Control.Constants;

/**
 * Created by tanmay on 21/1/17.
 */

public class AccountInformation {
    static String lastName="Guest Login",phoneNo="",email="",FirstName="",url="";
    static boolean lastNameEnabled=false,phoneNoEnabled=false,emailEnabled=false;
    static boolean loginUsingGmail=false, loginUsingFacebook=false;

    public static boolean isLoginUsingFacebook() {
        return loginUsingFacebook;
    }

    public static void setLoginUsingFacebook(boolean loginUsingFacebook) {

        AccountInformation.loginUsingFacebook = loginUsingFacebook;
    }

    public AccountInformation() {
    }

    public static void setLoginUsingGmail(boolean loginUsingGmail) {

        AccountInformation.loginUsingGmail = loginUsingGmail;
    }

    public static boolean isLoginUsingGmail() {

        return loginUsingGmail;
    }

    public static void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public static String getFirstName() {

        return FirstName;
    }

    public static void setUrl(String url) {
        AccountInformation.url = url;
    }

    public static String getUrl() {
    
        return url;
    }

    public AccountInformation(String FirstName, String lastName, String phoneNo, String email, boolean lastNameEnabled, boolean phoneNoEnabled, boolean emailEnabled, String url,boolean loginUsingGmail,boolean loginUsingFacebook) {
        this.FirstName=FirstName;
        this.lastName=lastName;
        this.phoneNo=phoneNo;
        this.email=email;
        this.lastNameEnabled=lastNameEnabled;
        this.phoneNoEnabled=phoneNoEnabled;
        this.emailEnabled=emailEnabled;
        this.url=url;
        this.loginUsingGmail=loginUsingGmail;
        this.loginUsingFacebook=loginUsingFacebook;
        
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastNameEnabled(boolean lastNameEnabled) {
        AccountInformation.lastNameEnabled = lastNameEnabled;
    }

    public static void setPhoneNoEnabled(boolean phoneNoEnabled) {
        AccountInformation.phoneNoEnabled = phoneNoEnabled;
    }

    public static void setEmailEnabled(boolean emailEnabled) {
        AccountInformation.emailEnabled = emailEnabled;
    }

    public static boolean isLastNameEnabled() {

        return lastNameEnabled;
    }

    public static boolean isPhoneNoEnabled() {
        return phoneNoEnabled;
    }

    public static boolean isEmailEnabled() {
        return emailEnabled;
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
