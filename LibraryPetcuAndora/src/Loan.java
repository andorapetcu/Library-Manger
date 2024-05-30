import java.sql.Date;

public class Loan {
    protected int loanId;
    protected int bookId;
    protected int readerId;
    protected java.sql.Date loanDate;
    protected java.sql.Date returnDate;
    protected boolean active;

    public Loan(int loanId, int bookId, int readerId, Date loanDate, Date returnDate, boolean active) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.readerId = readerId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.active = active;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", bookId=" + bookId +
                ", readerId=" + readerId +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", active=" + active +
                '}';
    }
}
