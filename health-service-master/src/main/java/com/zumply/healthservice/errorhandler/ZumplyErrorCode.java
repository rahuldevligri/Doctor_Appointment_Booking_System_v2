package com.zumply.healthservice.errorhandler;

public enum ZumplyErrorCode {

    DOCTOR_ALREADY_EXISTS("DOCTOR_ALREADY_EXISTS", "doctor is already exist with registration number: "),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "doctor is already exist with Email: "),
    CATEGORY_ALREADY_EXISTS("CATEGORY_ALREADY_EXISTS", "Category is already exist with name: "),
    HOSPITAL_ALREADY_EXISTS("HOSPITAL_ALREADY_EXISTS", "Hospital is already exist with name: "),
    PHONE_ALREADY_EXISTS("PHONE_ALREADY_EXISTS", "doctor is already exist with phone number: "),
    DOCTOR_NOT_FOUND("DOCTOR_NOT_FOUND", "Doctor  not found "),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "Category not found for id  "),
    HOSPITAL_NOT_FOUND("HOSPITAL_NOT_FOUND", "Hospital not found for id  "),
    TABLE_IS_EMPTY("TABLE_IS_EMPTY", "Table is empty"),
    APPOINTMENT_NOT_FOUND("APPOINTMENT_NOT_FOUND", "APPOINTMENT IS NOT BOOKED YET FOR THIS ID "),
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found for id  "),
    DOCTOR_SCHEDULE_NOT_FOUND("DOCTOR_SCHEDULE_NOT_FOUND", "Doctor Schedule  not found for id  ");

    private String errorCode;
    private String errorMessage;

    ZumplyErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
