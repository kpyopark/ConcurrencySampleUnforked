package com.theironyard;

public class RyanAndMonicaJob implements Runnable {

  private BankAccount account = new BankAccount();
  // There will be only one instance of the RyanAndMonicaJob. That means only
  // one instance of the bank account. Both threads will access this one account.

  public static void main(String[] args) {
    RyanAndMonicaJob theJob = new RyanAndMonicaJob();
    // Instantiate the Runnable(job.)
    Thread one = new Thread(theJob);
    Thread two = new Thread(theJob);
    // Make two threads, giving each thread the same Runnable job. That means
    // both threads will access this one account.
    one.setName("Ryan");
    two.setName("Monica");
    one.start();
    two.start();
  }// close main

  public void run() {
    for (int x = 0; x < 10; x++) {
      makeWithdrawal(10);
      if (account.getBalance() < 0) {
        System.out.println("Overdrawn!");
      }
      // In the run() method, a thread loops through and tries to make a withdrawal
      // with each iteration. After the withdrawal, it checks the balance once again
      // to see if the account is overdrawn.
    }
  }// close run()

  private void makeWithdrawal(int amount) {
    if (account.getBalance() >= amount) {
      System.out.println(Thread.currentThread().getName() + " is about to withdraw");
      // Check the account balance, and if there's not enough money, we just print a
      // message.
      // If there IS enough, we go to sleep, then wake up and complete the withdrawal,
      // just like Ryan did.
      try {
        System.out.println(Thread.currentThread().getName() + " is going to sleep");
        Thread.sleep(500);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + " woke up.");
      account.withdraw(amount);
      System.out.println(Thread.currentThread().getName() + " completes the withdrawal");
    } else {
      System.out.println("Sorry, not enough for " + Thread.currentThread().getName());
    }
  }// close makeWithdrawal()
}// close class