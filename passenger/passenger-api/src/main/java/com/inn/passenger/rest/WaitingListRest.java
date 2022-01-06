package com.inn.passenger.rest;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inn.passenger.model.WaitingList;

@RequestMapping("/rest/waitingList")
public interface WaitingListRest {

    @PostMapping("/create")
    void addToWaitingList(@RequestBody WaitingList wl);

    Optional<WaitingListRest> getAllWLByPassengerId(@PathVariable Integer passengerId);



}
