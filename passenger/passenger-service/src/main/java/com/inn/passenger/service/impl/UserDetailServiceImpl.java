package com.inn.passenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.passenger.dao.UserDetailRepository;
import com.inn.passenger.model.UserDetail;
import com.inn.passenger.service.UserDetailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailService {

  @Autowired
  UserDetailRepository userDetailRepository;

  @Override
  public UserDetail create(UserDetail userDetail) {
    log.info("Inside the UserDetailServiceImpl :{}", userDetail);
    return userDetailRepository.save(userDetail);
  }

  @Override
  public UserDetail update(UserDetail userDetail) {
    log.info("Inside the UserDetailServiceImpl :{}", userDetail);
    return userDetailRepository.save(userDetail);
  }

}
