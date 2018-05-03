package dao;

import core.Account;

import java.util.List;

public interface AccountDao {
    void save(Account t);
    void delete (Account t);
    void update (Account t);
    Account findById(Integer id);
    List<Account> findAll();

}
