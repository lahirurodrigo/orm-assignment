package lk.ijse.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Borrowals {

    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    private LocalDate borrow_date;

    @Temporal(TemporalType.DATE)
    private LocalDate due_date;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "borrowal_details",
            joinColumns = @JoinColumn(name = "borrowal_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    public Borrowals() {
    }

    public Borrowals(String id, LocalDate borrow_date, LocalDate due_date, Member member, List<Book> books) {
        this.id = id;
        this.borrow_date = borrow_date;
        this.due_date = due_date;
        this.member = member;
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Borrowals{" +
                "id='" + id + '\'' +
                ", borrow_date=" + borrow_date +
                ", due_date=" + due_date +
                ", member=" + member +
                ", books=" + books +
                '}';
    }
}
