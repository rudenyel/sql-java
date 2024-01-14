package cz.murkaliza.jdbc;

import cz.murkaliza.jdbc.utils.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDAO extends DataAccessObject<Book> {
    private static final String INSERT =
            "INSERT INTO books (title, first_name, last_name)" +
            "VALUES (?, ?, ?)";
    private static final String GET_ONE =
            "SELECT id, title, first_name, last_name " +
            "FROM books WHERE id = ?";

    private static final String UPDATE =
            "UPDATE books SET title = ?, first_name = ?, last_name = ? " +
            "WHERE id = ?";

    private static final String DELETE = "DELETE FROM books WHERE id = ?";


    public BookDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Book findById(long id) {
        Book customer = new Book();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                customer.setId(rs.getLong("id"));
                customer.setTitle(rs.getString("title"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book update(Book dto) {
        Book book = null;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE);) {
            statement.setString(1, dto.getTitle());
            statement.setString(2, dto.getFirstName());
            statement.setString(3, dto.getLastName());
            statement.setLong(4, dto.getId());
            statement.execute();
            book = this.findById(dto.getId());
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public Book create(Book dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT)) {
            statement.setString(1, dto.getTitle());
            statement.setString(2, dto.getFirstName());
            statement.setString(3, dto.getLastName());
            statement.execute();
            int id = this.getLastId("books");
            return this.findById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE);){
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
