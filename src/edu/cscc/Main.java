package edu.cscc;

import edu.cscc.transactions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("." + File.separator + "rental_transactions.csv");
        TransactionsReader transactionsReader = new TransactionsReader(fileReader);

        TransactionAccessor transactionAccessor = new TransactionAccessor(transactionsReader);

        List<String> dates = Arrays.asList(
                "2020-09-07",
                "2020-09-08",
                "2020-09-09",
                "2020-09-10",
                "2020-09-11",
                "2020-09-12",
                "2020-09-13"
        );
        List<RunnableTransactionListBuilder> runnableTransactionListBuilders =
                createTransactionListBuilders(transactionAccessor, dates);

        List<Thread> threads = createThreads(runnableTransactionListBuilders);
        startThreads(threads);

        waitForAllThreadsToComplete(threads);

        transactionsReader.close();
        fileReader.close();

        printDailyStats(runnableTransactionListBuilders);
        printTotalStats(runnableTransactionListBuilders);
    }

    private static void waitForAllThreadsToComplete(List<Thread> threads) {
        // TODO - Implement me
    }

    private static void startThreads(List<Thread> threads) {
        // TODO - Implement me
    }

    private static List<RunnableTransactionListBuilder> createTransactionListBuilders(
            TransactionAccessor transactionAccessor, List<String> dates) {
        return dates
                .stream()
                .map(date -> new RunnableTransactionListBuilder(transactionAccessor, date))
                .collect(Collectors.toList());
    }

    private static List<Thread> createThreads(List<RunnableTransactionListBuilder> transactionListBuilders) {
        // TODO - Implement me
        return null;
    }

    private static void printDailyStats(List<RunnableTransactionListBuilder> runnableTransactionListBuilders) {
        runnableTransactionListBuilders.forEach(transactionListBuilder -> {
            printTransactionStats(transactionListBuilder.getDate(),
                    TransactionStatsGenerator.generateStats(transactionListBuilder.getTransactions()));
        });
    }

    private static void printTotalStats(List<RunnableTransactionListBuilder> runnableTransactionListBuilders) {
        ArrayList transactions = new ArrayList();
        runnableTransactionListBuilders.forEach(transactionListBuilder -> {
            transactions.addAll(transactionListBuilder.getTransactions());
        });
        printTransactionStats("TOTAL", TransactionStatsGenerator.generateStats(transactions));

    }


    private static void printTransactionStats(String day, TransactionStats stats) {
        System.out.println(day + " stats:");
        System.out.println("Total rentals: " + stats.getTotalRentals());
        System.out.println("Most popular movie: " + stats.getMostPopularMovie());
        System.out.println("Best employee: " + stats.getBestEmployee());
        System.out.println("Best store: " + stats.getBestStore());
        System.out.println("Gross revenue: " + stats.getTotalRevenue());
        System.out.println();
    }


}
