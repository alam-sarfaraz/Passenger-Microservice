package com.inn.passenger.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateTicket {

	private Integer noOfSeat;

	@NotBlank(message="Date is mandatory")
	private String date;

	@NotBlank(message="Source is mandatory")
	private String source;

	@NotBlank(message="Destination is mandatory")
	private String destination;

	@NotBlank(message="Train Number is mandatory")
	private String trainNumber;

	private List<UserDetail> userDetail;

}
