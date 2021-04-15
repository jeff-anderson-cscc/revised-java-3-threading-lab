package edu.cscc.transactions;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TransactionAccessor {

    private final Queue<Transaction> transactionQueue;
    private Iterator<Transaction> iterator;

    public TransactionAccessor(TransactionsReader transactionsReader) {
        this.transactionQueue = new ConcurrentLinkedQueue<>();
        iterator = transactionsReader.iterator();
        if (hasMoreRecords()) {
            readRecord();
        }
    }

    // TODO - Make the methods in this class thread safe:

    public Transaction peek() {
        return transactionQueue.peek();
    }

    public boolean hasNext() {
        return peek() != null;
    }

    public Transaction next() {
        Transaction transaction = transactionQueue.poll();
        if (hasMoreRecords()) {
            readRecord();
        }

        return transaction;
    }

    private boolean hasMoreRecords() {
        return iterator.hasNext();
    }

    private  void readRecord() {
        transactionQueue.offer(iterator.next());
    }
}
