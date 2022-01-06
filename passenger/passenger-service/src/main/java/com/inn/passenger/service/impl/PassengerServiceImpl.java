package com.inn.passenger.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.passenger.dao.PassengerDao;
import com.inn.passenger.exceptions.CustomException;
import com.inn.passenger.model.BookedTicket;
import com.inn.passenger.model.GenerateTicket;
import com.inn.passenger.model.UserDetail;
import com.inn.passenger.service.PassengerService;
import com.inn.passenger.service.UserDetailService;
import com.inn.passenger.utils.PassengerUtils;
import com.inn.passenger.validation.Validation;
import com.inn.train.client.model.TrainDetail;
import com.inn.train.client.rest.TrainRest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {

  @Autowired
  PassengerDao passengerDao;

  @Autowired
  TrainRest restTrainRest;

  @Autowired
  UserDetailService userDetailService;

  @Override
  public BookedTicket generateTicket(GenerateTicket genrateTicket) throws Exception {
    try {
      log.info("Inside the PassengerServiceImpl of generateTicket :{}", genrateTicket);
      BookedTicket bookedTicket = new BookedTicket();
      TrainDetail trainDetailByDateSrcAndDest = restTrainRest.getTrainDetailByTrainNoDateSrcAndDest(
          genrateTicket.getTrainNumber(), genrateTicket.getDate(), genrateTicket.getSource(),
          genrateTicket.getDestination());
      log.info("From the train microservice data {}", trainDetailByDateSrcAndDest);

      if (trainDetailByDateSrcAndDest == null)
        throw new CustomException("No Train are available");
      String status = trainDetailByDateSrcAndDest.getStatus();

      if (status.equalsIgnoreCase("CANCELLED"))
        throw new CustomException("Train is cancelled");

      Integer totalSeatOfTrain = trainDetailByDateSrcAndDest.getTotalSeat();
      log.info("Total no of seat of train {}", totalSeatOfTrain);

      Integer totalbookedTicket = passengerDao.seatCountBooked(genrateTicket.getTrainNumber());
      log.info("Total seat booked {}", totalbookedTicket);

      log.info("Generate PRN :{}", Validation.generatePnr());
      String generatePnr = Validation.generatePnr();

      if (totalSeatOfTrain <= totalbookedTicket) {
        bookedTicket.setStatus("WL");
        bookedTicket.setSeatNo("Waiting List");
        bookedTicket.setBerth("No seat is available");
      } else {
        bookedTicket.setStatus("CONFIRMED");
        bookedTicket.setSeatNo(Integer.toString(totalbookedTicket + 1));
        bookedTicket.setBerth(Validation.berthAllocated(totalbookedTicket + 1));
      }
      bookedTicket.setPnr(generatePnr);
      bookedTicket.setSeatBooked(genrateTicket.getNoOfSeat());
      bookedTicket.setDate(genrateTicket.getDate());
      bookedTicket.setSource(genrateTicket.getSource());
      bookedTicket.setDestination(genrateTicket.getDestination());
      bookedTicket.setTrainNumber(genrateTicket.getTrainNumber());
      bookedTicket.setTrainName(trainDetailByDateSrcAndDest.getTrainName());
      bookedTicket.setArrivalTime(trainDetailByDateSrcAndDest.getArrivalTime());
      bookedTicket.setDepartureTime(trainDetailByDateSrcAndDest.getDepartureTime());
      bookedTicket.setTotalFair(genrateTicket.getNoOfSeat() * trainDetailByDateSrcAndDest.getTotalFair());
      bookedTicket.setUserDetail(genrateTicket.getUserDetail());
      log.info("Values of booked UserDetail :{}", bookedTicket.getUserDetail());
      String savedTicketIntoFile = PassengerUtils.savedTicketIntoFile(bookedTicket);
      bookedTicket.setFilePath(savedTicketIntoFile);
      BookedTicket booked = passengerDao.save(bookedTicket);
      log.info("After booked ticket  :{}", booked);
      List<UserDetail> userDetails = booked.getUserDetail();
      for (UserDetail userDetail : userDetails) {
        userDetail.setBookedTicket(bookedTicket);
        userDetailService.update(userDetail);
      }
      return booked;
    } catch (Exception e) {
      log.info("Error occured due to the {}", e.getMessage());
      throw e;
    }
  }

  @Override
  public BookedTicket findByPnr(String pnr) throws Exception {
    try {
      log.info("Inside the PassengerServiceImpl of the findByPnr :{}", pnr);
      BookedTicket ticketDetail = passengerDao.findByPnr(pnr);
      if (ticketDetail == null) {
        throw new CustomException("Invalid PNR number");
      }
      return ticketDetail;
    } catch (Exception e) {
      log.info("Error due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Integer seatCountBooked(String trainNumber) {
    try {
      log.info("Inside the PassengerServiceImpl of the seatCountBooked :{}", trainNumber);
      return passengerDao.seatCountBooked(trainNumber);
    } catch (Exception e) {
      log.info("Error due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Optional<BookedTicket> getTicketById(Integer id) throws Exception {
    try {
      log.info("Inside the PassengerServiceImpl of the getTicketById :{}", id);
      Optional<BookedTicket> findById = passengerDao.findById(id);
      if (findById.isEmpty()) {
        throw new CustomException("Id does not exist..");
      }
      return findById;
    } catch (Exception e) {
      log.info("Error due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public String deleteTicketById(Integer id) {
    try {
      log.info("Inside the PassengerServiceImpl of the deleteTicketById :{}", id);
      Optional<BookedTicket> findById = passengerDao.findById(id);
      if (findById.isEmpty()) {
        return "Unable to delete the ticket Id not exist";
      }
      passengerDao.deleteById(id);
      return "Ticket Delete SuccssFully";
    } catch (Exception e) {
      log.info("Error due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Integer generateWL(String status) {
    try {
      log.info("Inside the PassengerServiceImpl of the generateWL :{}", status);
      return passengerDao.generateWL(status);
    } catch (Exception e) {
      log.info("Error due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public ResponseEntity<byte[]> downloadTicket(String pnr) {
    try {
      log.info("Inside the PassengerServiceImpl of the downloadTicket :{}", pnr);
      BookedTicket findByPnr = passengerDao.findByPnr(pnr);
      return PassengerUtils.downloadFile(findByPnr.getFilePath());
    } catch (Exception e) {
      log.info("Error due to :{}", e.getMessage());
      throw e;
    }
  }

}
