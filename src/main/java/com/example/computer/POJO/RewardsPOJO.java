package com.example.computer.POJO;

public class RewardsPOJO {
    private long customerId;
    private long lastMonthRewardPoints;
    private long lastSecondRewardPoints;
    private long lastThirdRewardPoints;
    private long totalRewards;

    public RewardsPOJO(){}

    public long getCustomerId() {
        return this.customerId;
    }

    public long getLastMonthRewardPoints() {
        return this.lastMonthRewardPoints;
    }

    public void setLastMonthRewardPoints(long lastMonthRewardPoints) {
        this.lastMonthRewardPoints = lastMonthRewardPoints;
    }

    public long getLastSecondMonthRewardPoints() {
        return this.lastSecondRewardPoints;
    }

    public void setLastSecondMonthRewardPoints(long lastSecondMonthRewardPoints) {
        this.lastSecondRewardPoints = lastSecondMonthRewardPoints;
    }

    public long getLastThirdMonthRewardPoints() {
        return this.lastThirdRewardPoints;
    }

    public void setLastThirdMonthRewardPoints(long lastThirdMonthRewardPoints) {
        this.lastThirdRewardPoints = lastThirdMonthRewardPoints;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getTotalRewards() {
        return this.totalRewards;
    }

    public void setTotalRewards(long totalRewards) {
        this.totalRewards = totalRewards;
    }

}
