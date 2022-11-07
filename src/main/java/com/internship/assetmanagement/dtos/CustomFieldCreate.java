package com.internship.assetmanagement.dtos;


import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CustomFieldCreate {


    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String value;
}
