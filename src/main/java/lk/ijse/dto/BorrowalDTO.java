package lk.ijse.dto;

import java.time.LocalDate;

public class BorrowalDTO {

    private String id;
    private String memberId;
    private String book1Id;
    private String book2Id;
    private LocalDate date;
    private LocalDate duedate;

    public BorrowalDTO() {
    }

    public BorrowalDTO(String id, String memberId, String book1Id, String book2Id, LocalDate date, LocalDate duedate) {
        this.id = id;
        this.memberId = memberId;
        this.book1Id = book1Id;
        this.book2Id = book2Id;
        this.date = date;
        this.duedate = duedate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBook1Id() {
        return book1Id;
    }

    public void setBook1Id(String book1Id) {
        this.book1Id = book1Id;
    }

    public String getBook2Id() {
        return book2Id;
    }

    public void setBook2Id(String book2Id) {
        this.book2Id = book2Id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDuedate() {
        return duedate;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    @Override
    public String toString() {
        return "BorrowalDTO{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", book1Id='" + book1Id + '\'' +
                ", book2Id='" + book2Id + '\'' +
                ", date=" + date +
                ", duedate=" + duedate +
                '}';
    }
}
