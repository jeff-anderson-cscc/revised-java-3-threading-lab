package edu.cscc.transactions;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class TransactionStatsGenerator {

    private TransactionStatsGenerator() {
    }

    public static TransactionStats generateStats(List<Transaction> transactions) {
        TransactionStats transactionStats = new TransactionStats();

        transactionStats.setTotalRentals(transactions.size());
        transactionStats.setBestEmployee(getBestEmployee(transactions));
        transactionStats.setBestStore(getBestStore(transactions));
        transactionStats.setTotalRevenue(getTotalRevenue(transactions));
        transactionStats.setMostPopularMovie(getMostPopularMovie(transactions));

        return transactionStats;
    }

    private static String getMostPopularMovie(List<Transaction> transactions) {
        Map<String, List<Transaction>> rentalsGroupedByName =
                transactions
                        .stream()
                        .collect(Collectors.groupingBy(item -> item.getRentalName()));
        Optional<Map.Entry<String, List<Transaction>>> bestByCount = bestByCount(rentalsGroupedByName);

        return bestByCount.get().getKey();
    }

    private static Double getTotalRevenue(List<Transaction> transactions) {
        return transactions
                .stream()
                .map(transaction -> transaction.getRentalCost())
                .reduce(0d, (accum, item) -> accum + item);
    }

    private static String getBestStore(List<Transaction> transactions) {
        Map<String, List<Transaction>> rentalsByStore = transactions
                .stream()
                .collect(Collectors.groupingBy(item -> item.getStoreNumber()));
        Optional<Map.Entry<String, List<Transaction>>> bestByCount = bestByCount(rentalsByStore);

        return bestByCount.get().getKey();
    }

    private static String getBestEmployee(List<Transaction> transactions) {
        Map<String, List<Transaction>> rentalsGroupByEmployee =
                transactions
                        .stream()
                        .collect(Collectors.groupingBy(item -> item.getEmployeeId()));
        Optional<Map.Entry<String, List<Transaction>>> bestByCount = bestByCount(rentalsGroupByEmployee);

        return bestByCount.get().getKey();
    }

    private static Optional<Map.Entry<String, List<Transaction>>> bestByCount(Map<String, List<Transaction>> rentalsGroupedByName) {
        return rentalsGroupedByName.entrySet()
                .stream()
                .sorted((first, second) -> {
                    if (first.getValue().size() > second.getValue().size()) {
                        return 1;
                    } else if (first.getValue().size() < second.getValue().size()) {
                        return -1;
                    }

                    return 0;
                }).findFirst();
    }

}
