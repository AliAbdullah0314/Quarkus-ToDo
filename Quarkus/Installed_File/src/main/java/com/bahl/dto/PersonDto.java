package com.bahl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PersonDto {
        
    public String personName;
    public String password;
    public Integer id;
    public String dateOfBirth;
    public String cnicNumber;
    public Boolean status; 
    //String tasks[]; 
    // public Person(String name, int id) {
    //     this.name = name;
    //     Id = id;
    // }

}
