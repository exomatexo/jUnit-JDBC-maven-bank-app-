package dao;

import core.Client;

import java.util.List;

public interface ClientDao {
    void save(Client t);
    void delete (Client t);
    void update (Client t);
    Client findById(Integer id);
    List<Client> findAll();
}
