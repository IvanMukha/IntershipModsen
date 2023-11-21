package test.Modsen.models;

import java.time.LocalDateTime;
import java.util.Date;

public class BookRentalInfo {

public BookRentalInfo(){}
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Date getTimeOfRent() {
        return timeOfRent;
    }

    public void setTimeOfRent(Date timeOfRent) {
        this.timeOfRent = timeOfRent;
    }

    public BookRentalInfo(int id, Date timeOfRent, Date timeOfReturn) {
        this.id = id;
        this.timeOfRent = timeOfRent;
        this.timeOfReturn = timeOfReturn;
    }

    public Date getTimeOfReturn() {
        return timeOfReturn;
    }

    public void setTimeOfReturn(Date timeOfReturn) {
        this.timeOfReturn = timeOfReturn;
    }

    private Date timeOfRent;
    private Date timeOfReturn;





}

