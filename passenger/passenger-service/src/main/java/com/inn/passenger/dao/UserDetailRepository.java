package com.inn.passenger.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.passenger.model.UserDetail;

@Repository
public interface UserDetailRepository  extends JpaRepository<UserDetail, Integer>{

}
