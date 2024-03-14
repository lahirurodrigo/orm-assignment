package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Member {

    @Id
    private String id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<Borrowals> borrowals;

    public Member() {
    }

    public Member(String id, String name, String email, List<Borrowals> borrowals) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowals = borrowals;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Borrowals> getBorrowals() {
        return borrowals;
    }

    public void setBorrowals(List<Borrowals> borrowals) {
        this.borrowals = borrowals;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", borrowals=" + borrowals +
                '}';
    }
}
