package com.inn.train.client.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotBlank(message = "Train Number is mandatory")
	@Size(max = 5, message = "Train Number is not max than 5 number")
	@Column(name = "TRAIN_NUMBER")
	private String trainNumber;

	@NotBlank(message = "Train Name is mandatory")
	@Column(name = "TRAIN_NAME")
	private String trainName;

	@NotBlank(message = "Source field is mandatory")
	@Column(name = "SOURCE")
	private String source;

	@NotBlank(message = "Destination field is mandatory")
	@Column(name = "DESTINATION")
	private String destination;

	@Column(name = "DATE")
	private String date;

	@NotBlank(message = "Arrival Time is mandatory")
	@Column(name = "ARRIVAL_TIME")
	private String arrivalTime;

	@NotBlank(message = "Departure Time is mandatory")
	@Column(name = "DEPARTURE_TIME")
	private String departureTime;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "TOTAL_SEAT")
	private Integer totalSeat;

	@Column(name = "TOTAL_FAIR")
	private Double totalFair;

}
