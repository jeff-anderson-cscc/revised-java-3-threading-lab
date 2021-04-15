package edu.cscc.transactions;

public class TransactionStats {
    private int totalRentals;
    private String mostPopularMovie;
    private String bestEmployee;
    private String bestStore;
    private Double totalRevenue;

    public int getTotalRentals() {
        return totalRentals;
    }

    public void setTotalRentals(int totalRentals) {
        this.totalRentals = totalRentals;
    }

    public String getMostPopularMovie() {
        return mostPopularMovie;
    }

    public void setMostPopularMovie(String mostPopularMovie) {
        this.mostPopularMovie = mostPopularMovie;
    }

    public String getBestEmployee() {
        return bestEmployee;
    }

    public void setBestEmployee(String bestEmployee) {
        this.bestEmployee = bestEmployee;
    }

    public String getBestStore() {
        return bestStore;
    }

    public void setBestStore(String bestStore) {
        this.bestStore = bestStore;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
