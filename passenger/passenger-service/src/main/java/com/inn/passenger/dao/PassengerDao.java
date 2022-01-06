package com.inn.passenger.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inn.passenger.model.BookedTicket;

@Repository
public interface PassengerDao extends JpaRepository<BookedTicket, Integer> {

	@Query("select bt from BookedTicket bt where bt.pnr =:pn")
	public BookedTicket findByPnr(@Param("pn") String pnr);

	@Query("select count(bt) from BookedTicket bt where bt.trainNumber =:c")
	public Integer seatCountBooked(@Param("c") String trainNumber);

	@Query("select count(bt) from BookedTicket bt where bt.status =:s")
	public Integer generateWL(@Param("s") String status);

}
