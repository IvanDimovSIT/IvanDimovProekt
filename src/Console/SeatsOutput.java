package Console;

import com.company.Seat;

import java.util.List;

public class SeatsOutput implements com.company.SeatsOutput {

    @Override
    public void print(List<Seat> seats) {
        if(seats.size()==0)System.out.println("No seats");
        else
        for (Seat seat: seats) {
            if(seat.getNote() != null && !seat.getNote().isEmpty())
                System.out.println(seat + "-" + seat.getNote());
            else
                System.out.println(seat);
        }
    }
}
