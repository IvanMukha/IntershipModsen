package test.Modsen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import test.Modsen.models.Book;
import test.Modsen.models.BookRentalInfo;

import java.time.LocalDate;
import java.util.List;

@Component
public class LibraryDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public LibraryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> getFreeBooks() {
             return jdbcTemplate.query("SELECT * FROM Book where person_id IS NULL ", new BeanPropertyRowMapper<>(Book.class));
    }

    public void release(int id){
        jdbcTemplate.update("DELETE FROM rentBook WHERE id=?",id);
    }
    public void assign(int id){
        jdbcTemplate.update("INSERT INTO rentBook(id,timeofrent,timeofreturn) VALUES(?,?,?)",id, LocalDate.now(),LocalDate.now().plusWeeks(2));
    }
    public List<BookRentalInfo> show(int id) {
        return jdbcTemplate.query("SELECT * FROM rentbook WHERE id = ?",new Object[]{id}, new BeanPropertyRowMapper<>(BookRentalInfo.class));
}}

