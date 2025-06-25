package com.example.reservation_service.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashMap;
import java.util.Objects;

public class TransferDto {

    @NotBlank(message = "Email cannot be empty")
    @Email
    private String emailReceiver;

    @NotBlank(message = "Type name can not be empty")
    private String typeName;

    private HashMap<String, String> params;

    @NotBlank
    private String username;

    public TransferDto(String emailReceiver, String typeName, HashMap<String, String> params, String username) {
        this.emailReceiver = emailReceiver;
        this.typeName = typeName;
        this.params = params;
        this.username = username;
    }

    // Getteri
    public String getEmailReceiver() {
        return emailReceiver;
    }

    public String getTypeName() {
        return typeName;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public String getUsername() {
        return username;
    }

    // Setteri
    public void setEmailReceiver(String emailReceiver) {
        this.emailReceiver = emailReceiver;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Equals i HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferDto that = (TransferDto) o;
        return emailReceiver.equals(that.emailReceiver) &&
                typeName.equals(that.typeName) &&
                params.equals(that.params) &&
                username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailReceiver, typeName, params, username);
    }

    // ToString
    @Override
    public String toString() {
        return "TransferDto{" +
                "emailReceiver='" + emailReceiver + '\'' +
                ", typeName='" + typeName + '\'' +
                ", params=" + params +
                ", username='" + username + '\'' +
                '}';
    }
}
