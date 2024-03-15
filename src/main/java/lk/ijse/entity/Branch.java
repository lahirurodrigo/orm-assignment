package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Branch {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "branch")
    private List<Book> books;

    public Branch() {
    }

    public Branch(String id, String name, Admin admin, List<Book> books) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                '}';
    }
}
