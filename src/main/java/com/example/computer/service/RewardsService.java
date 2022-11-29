package com.example.computer.service;

import com.example.computer.POJO.RewardsPOJO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface RewardsService {
    RewardsPOJO getRewardsByCustomerId(Long id);
}
