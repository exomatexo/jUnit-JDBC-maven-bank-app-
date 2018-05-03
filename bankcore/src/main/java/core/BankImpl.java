package core;

import java.util.ArrayList;

public class BankImpl implements Bank {
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private Integer id_number= 1;

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Account return_account(Integer id){
    for (Account a : accounts) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        throw new AccountIdException();
    }
    @Override
    public Integer createAccount(String name, String address) {
        Account account= new Account(name,address);
        account.setId(id_number);
        account.setAmount(0);
        accounts.add(account);
        id_number++;
        return account.getId();
    }


    @Override
    public Integer findAccount(String name, String address) {
        for (Account a : accounts) {
            if (a.getAdress().equals(address) && a.getName().equals(name)) {
                return a.getId();
            }
        }
        throw new AccountIdException();
    }


    @Override
    public void deposit(Integer id, long amount) {
        for (Account a : accounts)
            if (a.getId().equals(id)) {
                a.setAmount(a.getAmount()+amount);
                return;
            }
        throw  new AccountIdException();
    }

    @Override
    public Long getBalance(Integer id) {
        for (Account a : accounts)
            if (a.getId().equals(id)) {
                return a.getAmount();
            }
        throw new AccountIdException();
    }


    @Override
    public void withdraw(Integer id, long amount) {
        for (Account a : accounts) {
            if (a.getId().equals(id)) {
                if (a.getAmount() >= amount) {
                    a.setAmount(a.getAmount() - amount);
                    return;
                } else {
                    throw new InsufficientFundsException();
                }
            }
        }
        throw new AccountIdException();
    }


    @Override
    public void transfer(Integer idSource, Integer idDestination, long amount) {
        Account a = find(idSource);
        Account b = find(idDestination);
        if(a == null || b == null){
            throw new AccountIdException();
        }
        if (a.getAmount()>= amount){
            a.setAmount(a.getAmount()-amount);
            b.setAmount(b.getAmount()+amount);
        }
        else
            throw new InsufficientFundsException();
    }
    private  Account find(Integer id){
        for (Account a : accounts){
            if (a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }
}

