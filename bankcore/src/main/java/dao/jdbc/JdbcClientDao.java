/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

/**
 *
 * @author exomat
 */

import core.Client;
import dao.ClientDao;
import exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionFactory;


public class JdbcClientDao implements ClientDao {

    @Override
    public void save(Client t) {
        final String SQL = "insert into bank.client values (DEFAULT,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, t.getFirstName());
                statement.setString(2, t.getLastName());
                statement.setString(3, t.getPesel());
                statement.setString(4, t.getEmail());
                statement.executeUpdate();
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        t.setId(rs.getInt(1));
                    }
                } catch (SQLException ex) {
                    throw new DataAccessException(ex);
                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }
    
    @Override
    public void update(Client t) {
        final String SQL = "update bank.client set firstName = ?, lastName = ?, id = ?, pesel = ? , email = ?  where id = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, t.getFirstName());
                statement.setString(2, t.getLastName());
                statement.setInt(3, t.getId());
                statement.setString(4,t.getPesel());
                statement.setString(5,t.getEmail());
                statement.executeUpdate();
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public void delete(Client t) {
        final String SQL = "delete from bank.client where id=?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, t.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Client findById(Integer id) {
        final String SQL = "select * from bank.client where id = ?";
        Client result = null;
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    result = new Client();
                    result.setId(rs.getInt("id"));
                    result.setFirstName(rs.getString("firstName"));
                    result.setLastName(rs.getString("lastName"));
                    result.setPesel(rs.getString("pesel"));
                    result.setEmail(rs.getString("email"));
                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        return result;
    }

    @Override
    public List<Client> findAll() {
        final String SQL = "select * from bank.client";
        List<Client> results = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Client acc = new Client();
                    acc.setId(rs.getInt("id"));
                    acc.setFirstName(rs.getString("firstName"));
                    acc.setLastName(rs.getString("lastName"));
                    acc.setPesel(rs.getString("pesel"));
                    acc.setEmail(rs.getString("email"));
                    results.add(acc);
                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        return results;
    }

}