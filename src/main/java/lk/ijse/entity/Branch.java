package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Branch {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Branch() {
    }

    public Branch(String id, String name, Admin admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
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

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                '}';
    }
}
