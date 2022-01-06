package com.inn.passenger.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "BOOKED_TICKET")
public class BookedTicket implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 3242968149628196671L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "PNR_NUMBER")
  private String pnr;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "SEAT_NO")
  private String seatNo;

  @Column(name = "BERTH")
  private String berth;

  @Column(name = "NO_OF_SEAT_BOOKED")
  private Integer SeatBooked;

  @Column(name = "DATE")
  private String date;

  @Column(name = "SOURCE")
  private String source;

  @Column(name = "DESTINATION")
  private String destination;

  @Column(name = "TRAIN_NUMBER")
  private String trainNumber;

  @Column(name = "TRAIN_NAME")
  private String trainName;

  @Column(name = "ARRIVAL_TIME")
  private String arrivalTime;

  @Column(name = "DEPARTURE_TIME")
  private String departureTime;

  @Column(name = "TOTAL_FAIR")
  private Double totalFair;

  @Getter(onMethod_ = @JsonIgnore)
  @Column(name = "FILE_PATH")
  private String filePath;

  @OneToMany(mappedBy = "bookedTicket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<UserDetail> userDetail;

}
