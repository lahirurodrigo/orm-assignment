package lk.ijse.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    @JoinColumn(name = "borrowal_id")
    private Borrowals borrowals;

    @Temporal(TemporalType.DATE)
    private LocalDate due_date;

    @Temporal(TemporalType.DATE)
    private LocalDate return_date;

    public Return() {
    }

    public Return(String id, Borrowals borrowals, LocalDate due_date, LocalDate return_date) {
        this.id = id;
        this.borrowals = borrowals;
        this.due_date = due_date;
        this.return_date = return_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Borrowals getBorrowals() {
        return borrowals;
    }

    public void setBorrowals(Borrowals borrowals) {
        this.borrowals = borrowals;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "Return{" +
                "id='" + id + '\'' +
                ", borrowals=" + borrowals +
                ", due_date=" + due_date +
                ", return_date=" + return_date +
                '}';
    }
}
