package com.theironyard;

public class BankAccount {

  private int balance = 100;
  // the account starts with a balance of $100

  public int getBalance() {
    return balance;
  }

  public void withdraw(int amount) {
    balance = balance - amount;
  }
}