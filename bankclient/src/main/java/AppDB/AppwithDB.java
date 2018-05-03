/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppDB;

import core.Account;
import core.Client;
import dao.AccountDao;
import dao.ClientDao;
import dao.jdbc.JdbcAccountDao;
import dao.jdbc.JdbcClientDao;
import java.util.List;

/**
 *
 * @author exomat
 */
public class AppwithDB {
    public static void main(String[] args) {
        AccountDao dao = new JdbcAccountDao();
        Account test = new Account("matt","per");
        Account test2;
        Account test3 = new Account("per","nal");
        System.out.println("Try to save account ");
        dao.save(test);
        System.out.println("Saved");
        test2=dao.findById(test.getId());
        System.out.println("Found: "+test2.toString());
        dao.save(test);
        test.setAdress("kolonia");
        dao.update(test);
        dao.save(test3);
        List<Account> accounts = dao.findAll();
        for(Account acc : accounts){
            System.out.println(acc.toString());
        }
        dao.delete(test3);
        System.out.println("Deleting");
        List<Account> accounts2 = dao.findAll();
        for(Account acc : accounts2){
            System.out.println(acc.toString());
        }
        ClientDao dao_c = new JdbcClientDao();
        Client client = new Client();
        client.setFirstName("matt");
        client.setLastName("per");
        client.setPesel("987987");
        client.setEmail("email");
       
    }
    
}
