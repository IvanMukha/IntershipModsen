package test.Modsen.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Book {
    private int id;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @NotEmpty(message = "ISBN не должно быть пустым")
    private String ISBN;
    @NotEmpty(message = "Жанр не должно быть пустым")

    private String genre;
    @NotEmpty(message = "Описание не должно быть пустым")
    private String description;
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов длиной")
    private String title;

    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов длиной")
    private String author;



    public Book() {

    }

    public Book(String title, String author,String ISBN,String genre,String description) {
        this.title = title;
        this.author = author;
        this.ISBN=ISBN;
        this.genre=genre;
        this.description=description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}