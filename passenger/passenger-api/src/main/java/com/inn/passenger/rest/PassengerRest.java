package com.inn.passenger.rest;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inn.passenger.model.BookedTicket;
import com.inn.passenger.model.GenerateTicket;

@RequestMapping("/rest/passenger/")
public interface PassengerRest {

  @PostMapping("/bookedTicket")
  BookedTicket generateTicket(@RequestBody GenerateTicket genrateTicket) throws Exception;

  @GetMapping("findByPnr/{pnr}")
  BookedTicket findByPnr(@PathVariable("pnr") String pnr) throws Exception;

  @GetMapping("seatCount/{trainNumber}")
  Integer seatCountBooked(@PathVariable("trainNumber") String trainNumber);

  @GetMapping("getTicketById/{id}")
  Optional<BookedTicket> getTicketById(@PathVariable("id") Integer id) throws Exception;

  @DeleteMapping("deleteTicketById/{id}")
  String deleteTicketById(@PathVariable("id") Integer id);

  @GetMapping("/generateWL/{status}")
  Integer generateWL(@PathVariable("status") String status);

  @GetMapping(path = "/downloadTicket/{pnr}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  ResponseEntity<byte[]> downloadTicket(@PathVariable("pnr") String pnr);

}
