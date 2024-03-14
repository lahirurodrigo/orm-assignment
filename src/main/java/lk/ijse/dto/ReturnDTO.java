package lk.ijse.dto;

import java.time.LocalDate;

public class ReturnDTO {

    private String borrowalId;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public ReturnDTO() {
    }

    public ReturnDTO(String borrowalId, LocalDate dueDate, LocalDate returnDate) {
        this.borrowalId = borrowalId;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public String getBorrowalId() {
        return borrowalId;
    }

    public void setBorrowalId(String borrowalId) {
        this.borrowalId = borrowalId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "ReturnDTO{" +
                "borrowalId='" + borrowalId + '\'' +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
