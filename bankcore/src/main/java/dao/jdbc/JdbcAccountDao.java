package dao.jdbc;

import core.Account;
import dao.AccountDao;
import exception.DataAccessException;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcAccountDao implements AccountDao {
    @Override
    public void save(Account t) {
        final String SQL = "insert into bank.account values (DEFAULT,?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, t.getName());
                statement.setString(2, t.getAdress());
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
    public void update(Account t) {
        final String SQL = "update bank.account set namee = ?, address = ? where id = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, t.getName());
                statement.setString(2, t.getAdress());
                statement.setInt(3, t.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }


    @Override
    public void delete(Account t) {
        final String SQL = "delete from bank.account where id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, t.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Account findById(Integer id) {
        final String SQL = "select * from bank.account where id = ?";
        Account result = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    result = new Account();
                    result.setId(rs.getInt("id"));
                    result.setName(rs.getString("namee"));
                    result.setAdress(rs.getString("address"));
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
    public List<Account> findAll() {
        final String SQL = "select * from bank.account";
        List<Account> results = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Account acc = new Account();
                    acc.setId(rs.getInt("id"));
                    acc.setName(rs.getString("namee"));
                    acc.setAdress(rs.getString("address"));
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