package cz.murkaliza.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.ResultSet;


public class Main {

    public static void main(String... args) {
        try {

            Connection connection = DriverManager.getConnection("jdbc:sqlite:diary.db");
            BookDAO bookDAO = new BookDAO(connection);
            Book book = new Book();
            book.setTitle("text title");
            book.setFirstName("text first name");
            book.setLastName("text last name");
            bookDAO.create(book);

            Book another_book = bookDAO.findById(10);
            System.out.println(
                    another_book.getTitle() + "; " +
                            another_book.getFirstName() + "; " +
                            another_book.getLastName()
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM books");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
