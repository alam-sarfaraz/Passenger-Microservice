package com.inn.passenger.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER_DETAIL")
public class UserDetail implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 616280830786484731L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

	@Column(name="FIRST_NAME")
	@NotBlank(message="First Name is mandatory")
	private String firstName;

	@Column(name="MIDDLE_NAME")
	private String middleName;

	@Column(name="LAST_NAME")
	@NotBlank(message="Last Name is mandatory")
	private String lastName;

	@Column(name="GENDER")
	@NotBlank(message="Gender is mandatory")
	private String gender;

	@Column(name="MOBILE_NUMBER")
	@NotBlank(message="Mobile Number is mandatory")
	private String mobileNo;

	@Column(name="AGE")
	private Integer age;

	@Column(name="ID_PROOF")
	@NotBlank(message="Id Proof is mandatory")
	private String idProof;

	@Column(name="ID_PROOF_NUMBER")
	@NotBlank(message="Id proof  is mandatory")
	private String idProofNumber;

	@Column(name="ADDRESS")
	@NotBlank(message="Address  is mandatory")
	private String address;

	@ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PNR_NUMBER_FK", referencedColumnName = "PNR_NUMBER")
  @Getter(onMethod_ = @JsonIgnore)
  private BookedTicket bookedTicket;
}
