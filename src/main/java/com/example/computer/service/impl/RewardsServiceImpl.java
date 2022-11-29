package com.example.computer.service.impl;

import com.example.computer.POJO.RewardsPOJO;
import com.example.computer.common.CommonTools;
import com.example.computer.entity.Transaction;
import com.example.computer.repository.TransactionRepo;
import com.example.computer.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    TransactionRepo transactionRepo;

    @Override
    public RewardsPOJO getRewardsByCustomerId(Long id) {
        Timestamp lastMonth = this.getDateBasedOnOffSetDays(30);
        Timestamp lastSecond = this.getDateBasedOnOffSetDays(60);
        Timestamp lastThird = this.getDateBasedOnOffSetDays(90);

        List<Transaction> lastMonthTransactions = this.transactionRepo.findAllByCustomerIdAndTransactionDateBetween(id,
                lastMonth,Timestamp.from(Instant.now()));
        List<Transaction> lastSecondTransactions = this.transactionRepo.findAllByCustomerIdAndTransactionDateBetween(id,
                lastSecond,lastMonth);
        List<Transaction> lastThridTransactions = this.transactionRepo.findAllByCustomerIdAndTransactionDateBetween(id,
                lastThird, lastSecond);

        Long lastOneRewards = this.getRewardsPerMonth(lastMonthTransactions);
        Long lastTwoRewards = this.getRewardsPerMonth(lastSecondTransactions);
        Long lastThreeRewards = this.getRewardsPerMonth(lastThridTransactions);

        RewardsPOJO rewardsPOJO = new RewardsPOJO();
        rewardsPOJO.setCustomerId(id);
        rewardsPOJO.setLastMonthRewardPoints(lastOneRewards);
        rewardsPOJO.setLastSecondMonthRewardPoints(lastTwoRewards);
        rewardsPOJO.setLastThirdMonthRewardPoints(lastThreeRewards);
        rewardsPOJO.setTotalRewards(lastOneRewards + lastTwoRewards + lastThreeRewards);
        return rewardsPOJO;
    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays((long)days));
    }

    public Long getRewardsPerMonth(List<Transaction> transactions){
        return (Long)transactions.stream().map((transaction) -> {
            return this.calculatePoints(transaction);
        }).collect(Collectors.summingLong((r) -> {
            return r;
        }));
    }
    public Long calculatePoints(Transaction transaction){
        if(transaction.getTransactionAmount() > (double) CommonTools.firstRewardLimit && transaction.getTransactionAmount() <= (double)CommonTools.secondRewardLimit){
            return Math.round(transaction.getTransactionAmount() - (double) CommonTools.firstRewardLimit);

        }
        else{
            return transaction.getTransactionId() > (double) CommonTools.secondRewardLimit ? Math.round(transaction.getTransactionAmount() - (double)CommonTools.secondRewardLimit) * 2L + (long)(CommonTools.secondRewardLimit - CommonTools.firstRewardLimit) : 0L;
        }
    }
}
