package com.inn.passenger.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.inn.passenger.model.UserDetail;
import com.inn.passenger.rest.UserDetailRest;
import com.inn.passenger.service.UserDetailService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserDetailRestImpl implements UserDetailRest {

  @Autowired
  UserDetailService userDetailService;

  @Override
  public UserDetail create(UserDetail userDetail) {
    log.info("Inside the UserDetailRestImpl of create :{}", userDetail);
    return userDetailService.create(userDetail);
  }

  @Override
  public UserDetail update(UserDetail userDetail) {
    log.info("Inside the UserDetailRestImpl of update :{}", userDetail);
    return userDetailService.update(userDetail);
  }

}
