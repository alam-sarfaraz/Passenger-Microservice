package com.inn.passenger.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.inn.passenger.model.BookedTicket;
import com.inn.passenger.model.GenerateTicket;

public interface PassengerService {

  public BookedTicket generateTicket(GenerateTicket genrateTicket) throws Exception;

  public BookedTicket findByPnr(String pnr) throws Exception;

  public Integer seatCountBooked(String trainNumber);

  public Optional<BookedTicket> getTicketById(Integer id) throws Exception;

  public String deleteTicketById(Integer id);

  public Integer generateWL(String status);

  public ResponseEntity<byte[]> downloadTicket(String pnr);

 // public Integer getFirstWL(String status);

}
