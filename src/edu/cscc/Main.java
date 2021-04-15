package edu.cscc;

import edu.cscc.transactions.TransactionsReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("." + File.separator + "rental_transactions.csv");
            TransactionsReader transactionsReader = new TransactionsReader(fileReader);
            //TODO Implement the solution here.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
