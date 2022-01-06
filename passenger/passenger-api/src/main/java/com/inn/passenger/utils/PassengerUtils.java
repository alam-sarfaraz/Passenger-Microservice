package com.inn.passenger.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inn.passenger.model.BookedTicket;
import com.inn.passenger.model.UserDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PassengerUtils {

  public static String savedTicketIntoFile(BookedTicket bookedTicket) throws IOException {
    String path = "C:\\Management\\Ticket\\Download" + File.separator;
    File folder = new File(path);
    if (!folder.exists()) {
      folder.mkdirs();
    }
    String firstName = bookedTicket.getUserDetail().get(0).getFirstName() + "_";
    String middleName = bookedTicket.getUserDetail().get(0).getMiddleName() + "_";
    String lastName = bookedTicket.getUserDetail().get(0).getLastName();
    String pnr = bookedTicket.getPnr();
    String fileName = firstName + middleName + lastName + "_" + pnr + ".txt";
    File file = new File(folder, fileName);
    file.createNewFile();

    FileOutputStream fos = new FileOutputStream(file);
    OutputStreamWriter osw = new OutputStreamWriter(fos);
    osw.write("PNR : " + bookedTicket.getPnr().toUpperCase() + "\n");
    osw.write("TRAIN NAME : " + bookedTicket.getTrainName().toUpperCase() + "\n");
    osw.write("TRAIN NUMBER : " + bookedTicket.getTrainNumber().toUpperCase() + "\n");
    osw.write("DATE : " + bookedTicket.getDate().toUpperCase() + "\n");
    osw.write("DEPARTURE TIME : " + bookedTicket.getDepartureTime() + "\n");
    osw.write("ARRIVAL TIME : " + bookedTicket.getArrivalTime() + "\n");
    osw.write("STATUS : " + bookedTicket.getStatus().toUpperCase() + "\n");
    osw.write("SOURCE : " + bookedTicket.getSource().toUpperCase() + "\n");
    osw.write("DESTINATION : " + bookedTicket.getDestination().toUpperCase() + "\n");
    osw.write("SEAT NUMBER : " + bookedTicket.getSeatNo().toUpperCase() + "\n");
    osw.write("TOTAL SEAT BOOKED : " + bookedTicket.getSeatBooked() + "\n");
    osw.write("\n");
    osw.write("************** PASSENGER DETAILS ***********************" + "\n");
    int count = 0;
    for (UserDetail user : bookedTicket.getUserDetail()) {
      count = count + 1;
      osw.write("\n");
      osw.write("PASSENGER " + count + "\n");
      osw.write("\n");
      osw.write("NAME : " + (user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName()).toUpperCase()
          + "\n");
      osw.write("AGE : " + user.getAge() + "\n");
      osw.write("MOBILE NUMBER : " + user.getMobileNo() + "\n");
      osw.write("GENDER : " + user.getGender().toUpperCase() + "\n");
      osw.write("ID PROOF : " + user.getIdProof().toUpperCase() + "\n");
      osw.write(user.getIdProof().toUpperCase() + " NUMBER : " + user.getIdProofNumber() + "\n");
      osw.write("ADDRESS : " + user.getAddress().toUpperCase() + "\n");

    }
    osw.close();
    return path + fileName;
  }

  public static ResponseEntity<byte[]> downloadFile(String filePath) {
    log.info("Inside the PassengerUtils class :{}", filePath);
    try {
      File file = new File(filePath);
      String fileName = file.getName();
      log.info("downloadFile fileName : {}", fileName);
      byte[] fileByteArray = org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(file));
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
      return new ResponseEntity<>(fileByteArray, headers, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Error inside downloadFile : {}", e.getMessage());
      return null;
    }
  }

}
