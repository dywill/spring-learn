package com.dy.entity;

import java.math.BigDecimal;
import java.util.List;

public class Book {

    /**
     * id
     * book_name
     * price
     * stock
     */

    private Integer id;
    private String bookName;
    private BigDecimal price;
    private Integer stock;
    private Person person;

    private List<Person> persons;

    public Book() {
    }

    public Book(Integer id, String bookName, BigDecimal price, Integer stock) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
