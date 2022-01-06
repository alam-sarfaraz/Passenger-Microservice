package com.inn.passenger.rest.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.passenger.exceptions.CustomException;
import com.inn.passenger.model.BookedTicket;
import com.inn.passenger.model.GenerateTicket;
import com.inn.passenger.rest.PassengerRest;
import com.inn.passenger.service.PassengerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PassengerRestImpl implements PassengerRest {

  @Autowired
  PassengerService passengerService;

  @Override
  public BookedTicket generateTicket(GenerateTicket genrateTicket) throws Exception {
    try {
      log.info("Inside the PassengerRestImpl of the generateTicket :{}", genrateTicket);
      if (!genrateTicket.getNoOfSeat().equals(genrateTicket.getUserDetail().size())) {
        throw new CustomException("Please enter no entry equal to no of seat entered :" + genrateTicket.getNoOfSeat());
      }
      return passengerService.generateTicket(genrateTicket);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public BookedTicket findByPnr(String pnr) throws Exception {
    try {
      log.info("Inside the PassengerRestImpl of the findByPnr :{}", pnr);
      return passengerService.findByPnr(pnr);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Integer seatCountBooked(String trainNumber) {
    try {
      log.info("Inside the PassengerRestImpl of seatCountBooked :{}", trainNumber);
      return passengerService.seatCountBooked(trainNumber);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }

  }

  @Override
  public Optional<BookedTicket> getTicketById(Integer id) throws Exception {
    try {
      log.info("Inside the PassengerRestImpl of getTicketById :{}", id);
      return passengerService.getTicketById(id);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public String deleteTicketById(Integer id) {
    try {
      log.info("Inside the PassengerRestImpl of deleteById :{}", id);
      return passengerService.deleteTicketById(id);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }

  }

  @Override
  public Integer generateWL(String status) {
    try {
      log.info("Inside the PassengerRestImpl of generateWL :{}", status);
      return passengerService.generateWL(status);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public ResponseEntity<byte[]> downloadTicket(String pnr) {
    try {
      log.info("Inside the PassengerRestImpl of downloadTicket :{}", pnr);
      return passengerService.downloadTicket(pnr);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }
}
