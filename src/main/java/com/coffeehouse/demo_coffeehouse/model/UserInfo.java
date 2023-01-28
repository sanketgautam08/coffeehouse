package com.coffeehouse.demo_coffeehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfo{

    Integer userId;
    String lastName;
    String firstName;
    String userName;
}
