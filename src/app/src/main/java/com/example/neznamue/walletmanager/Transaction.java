package com.example.neznamue.walletmanager;

/**
 * Created by nezna on 5/27/2018.
 */

public class Transaction {

    private int id;
    private int type;
    private String title;
    private Double amount;

    public Transaction() {
    }

    public Transaction(int type, String title, Double amount) {
        this.type = type;
        this.title = title;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
