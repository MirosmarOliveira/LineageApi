package com.mirosmaroliveira.webapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountsDTO {

    @NotBlank
    private String login;
    @NotBlank
    private String email;
    @NotBlank
    private  String password;

}
