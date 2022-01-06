package com.inn.train.client.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.inn.train.client.model.TrainDetail;

@FeignClient(url = "http://localhost:4001/rest/train/", name = "RailwayMgmt")
public interface TrainRest {

	@GetMapping("getTrainDetailByTrainNumber/{trainNumber}")
	Optional<TrainDetail> getTrainDetailByTrainNumber(@PathVariable("trainNumber") String trainNumber) throws Exception;

	@GetMapping("getTrainDetailByTrainName/{trainName}")
	Optional<TrainDetail> getTrainDetailByTrainName(@PathVariable("trainName") String trainName) throws Exception;

	@GetMapping("getTrainBySourceAndDestination")
	List<TrainDetail> getTrainBySourceAndDestination(@RequestParam(name = "source", required = true) String source,
			@RequestParam(name = "destination", required = true) String destination) throws Exception;

	@GetMapping("getTrainDetailByDate")
	TrainDetail getTrainDetailByDate(@RequestParam(name = "date") String date) throws Exception;

	@GetMapping("getTrainDetailByDateSrcAndDest")
	List<TrainDetail> getTrainDetailByDateSrcAndDest(@RequestParam(name = "date", required = true) String date,
			@RequestParam(name = "source", required = true) String source,
			@RequestParam(name = "destination", required = true) String destination) throws Exception;

	@GetMapping("getTrainDetailByTrainNoDateSrcAndDest")
  TrainDetail getTrainDetailByTrainNoDateSrcAndDest(
      @RequestParam(name = "trainNumber", required = true) String trainNumber,
      @RequestParam(name = "date", required = true) String date,
      @RequestParam(name = "source", required = true) String source,
      @RequestParam(name = "destination", required = true) String destination) throws Exception;
}
