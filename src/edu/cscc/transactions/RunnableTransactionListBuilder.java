package edu.cscc.transactions;

import java.util.ArrayList;
import java.util.List;

public class RunnableTransactionListBuilder implements Runnable {
    private List<Transaction> transactionList = new ArrayList<>();
    private TransactionAccessor transactionAccessor;
    private String date;

    public RunnableTransactionListBuilder(TransactionAccessor transactionAccessor, String date) {
        this.transactionAccessor = transactionAccessor;
        this.date = date;
    }

    @Override
    public void run() {
        while (transactionAccessor.hasNext()) {
            Transaction transaction = transactionAccessor.peek();
            if (transaction != null && date.equals(transaction.getDate())) {
                transactionList.add(transactionAccessor.next());
            }
        }
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }

    public String getDate() {
        return date;
    }

}
