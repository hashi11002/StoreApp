package com.StoreBackend.StoreMain.DTO;

import lombok.Data;

@Data
public class signupDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private String username;
    private String password;
}
