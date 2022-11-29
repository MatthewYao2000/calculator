package com.example.computer.controller;

import com.example.computer.POJO.RewardsPOJO;
import com.example.computer.entity.Customer;
import com.example.computer.repository.CustomerRepo;
import com.example.computer.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController

public class RewardController {
    private final RewardsService rewardsService;
    private final CustomerRepo customerRepo;
    @Autowired
    public RewardController(RewardsService rewardsService, CustomerRepo customerRepo){
        this.rewardsService = rewardsService;
        this.customerRepo = customerRepo;
    }
    @GetMapping("/hello")
    public String hello()
        {
            return "hello";
        }

    @GetMapping("/search/{customerId}")
    public ResponseEntity<RewardsPOJO> getRewardsByCustomerId(@PathVariable("customerId") Long id){
        Customer customer = this.customerRepo.findByCustomerId(id);
        if(customer == null){
            throw  new RuntimeException("Customer id may not valid or missing id");
        }else{
            RewardsPOJO customerRewards = this.rewardsService.getRewardsByCustomerId(id);
            return new ResponseEntity(customerRewards, HttpStatus.OK);
        }
    }

}
