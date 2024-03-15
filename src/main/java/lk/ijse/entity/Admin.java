package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Admin {

    @Id
    private String id;
    @Column(unique = true)
    private String name;
    private String password;

    @OneToMany(mappedBy = "admin")
    private List<Branch> branches;

    public Admin() {
    }

    public Admin(String id, String name, String password, List<Branch> branches) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.branches = branches;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", branches=" + branches +
                '}';
    }
}
