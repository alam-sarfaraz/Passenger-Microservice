package com.inn.passenger.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inn.passenger.model.UserDetail;

@RequestMapping("/rest/user/")
public interface UserDetailRest {

  @PostMapping("/create")
  public UserDetail create(@RequestBody UserDetail userDetail);

  @PutMapping("/update")
  public UserDetail update(@RequestBody UserDetail userDetail);

}
