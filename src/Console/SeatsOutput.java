package Console;

import com.company.Seat;

import java.util.List;

public class SeatsOutput implements com.company.SeatsOutput {

    //отпечатване на списък с места
    @Override
    public void print(List<Seat> seats) {
        if(seats.size()==0)System.out.println("No seats");
        else
        for (Seat seat: seats) {
            if(seat.getNote() != null && !seat.getNote().isEmpty())
                System.out.println(seat + "\uD83D\uDCDD" + seat.getNote());
            else
                System.out.println(seat);
        }
    }
}

