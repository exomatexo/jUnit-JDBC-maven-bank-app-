import core.Bank;
import core.BankImpl;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class ConsoleApp {
    private Logger log = Logger.getLogger(ConsoleApp.class.getName());

    private static String ID_source = null, how_much, ID_dest;
    private BankImpl bank = new BankImpl( );

    public void menu() throws IOException {
        try {

            // This block configure the logger with handler and formatter
            FileHandler fh = new FileHandler("MyLogFile.log", 10 * 1024 * 1024, 10);
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);


            // the following statement is used to log any messages
            log.info("My first log");

        } catch (SecurityException e) {
            e.printStackTrace();
        }
        log.info("Starting program");
        String choose;
        do {
            System.out.println("core.Bank simulator ");
            System.out.println("Choose:");
            System.out.println("1.ADD account");
            System.out.println("2.FIND account");
            System.out.println("3.GET balance");
            System.out.println("4.WITHDRAW");
            System.out.println("5.DEPOSIT");
            System.out.println("6.TRANSFER");
            System.out.println("0.EXIT");
            Scanner sc = new Scanner(System.in);

            switch (choose = sc.nextLine()) {
                case "1":
                    String name ;
                    System.out.println("Full name: ");
                    name=sc.nextLine();
                    System.out.println("Adress: ");
                    String adress=sc.nextLine();
                    log.info("Creating account");
                    bank.createAccount(name,adress);
                    System.out.println("core.Account created");
                    log.info("core.Account created");
                    break;
                case "2":
                    System.out.println("Full name: ");
                    name=sc.nextLine();
                    System.out.println("Adress: ");
                    adress=sc.nextLine();
                    try{
                        log.info("Try to found out the account");
                        Integer a = bank.findAccount(name, adress);
                        System.out.println("This account has number: "+a);
                    }catch (Bank.AccountIdException e){
                        System.out.println("Can't find account");
                        log.warning("Didn't found account with that id"+e);
                    }
                    break;
                case "3":
                    System.out.println("Get Balance");
                    System.out.print("core.Account ID: ");
                    ID_source =sc.nextLine();
                    Long balance;
                    try {
                        log.info("Get balance function start");
                        balance = bank.getBalance(Integer.valueOf(ID_source));
                        System.out.println("core.Account balance: " + balance);
                    }catch (Bank.AccountIdException e){
                        System.out.println("Can't find account");
                        log.warning("Didn't found account with that id"+e);
                    }
                    break;
                case "4":
                    System.out.println("WITHDRAW money");
                    System.out.print("core.Account ID: ");
                    ID_source=sc.nextLine();
                    System.out.print("Amount: ");
                    how_much = sc.nextLine();
                    Long amount=null;
                    try{
                        log.info("Withdrow money function");
                        bank.withdraw(Integer.valueOf(ID_source), Long.parseLong(how_much));
                        System.out.println("Withdraw succesfull");
                    }catch (Bank.AccountIdException e){
                        System.out.println("Can't find account");
                        log.warning("Didn't found account with that id"+e);
                    }
                    catch (Bank.InsufficientFundsException g){
                        System.out.println("Don't enough money");
                        log.warning("Too low money on account "+g);
                    }
                    break;
                case "5":
                    System.out.println("Deposit: ");
                    System.out.print("core.Account ID: ");
                    ID_source=sc.nextLine();
                    System.out.print("Amount: ");
                    how_much = sc.nextLine();
                    try{
                        log.info("Deposit money function starts ");
                        bank.deposit(Integer.valueOf(ID_source), Long.parseLong(how_much));
                        System.out.println("Deposit succesfull");
                    }catch (Bank.AccountIdException e){
                        System.out.println("Can't find account");
                        log.warning("Didn't found account with that id"+e);
                    }
                    break;
                case "6":
                    System.out.println("Transfer");
                    System.out.println("From account: ");
                    ID_source=sc.nextLine();
                    System.out.println("To account: ");
                    ID_dest=sc.nextLine();
                    System.out.println("How much money? ");
                    how_much=sc.nextLine();
                    try{
                        log.info("Try to transfer money");
                        bank.transfer(Integer.valueOf(ID_source),Integer.valueOf(ID_dest),Long.parseLong(how_much));
                        System.out.println("Transfer succesfull!");
                    }catch (Bank.AccountIdException e){
                        System.out.println("Can't find account");
                        log.warning("Didn't found account with that id"+e);
                    }
                    catch (Bank.InsufficientFundsException g){
                        System.out.println("Don't enough money");
                        log.warning("Too low money on account "+g);
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Wrong choose!! Wrrrrrrrr!!");
            }

        }while(true);
    }
}