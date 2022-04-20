package Commands;

import com.company.Hall;
import com.company.Seat;
import com.company.SeatException;
import com.company.SeatState;

import java.util.ArrayList;
import java.util.List;

public class ListSeats implements FindSeats{
    @Override
    public List<Seat> findSeats(Hall hall, SeatState seatState) {
        if(hall == null || hall.getShowName() == null)return null;
        List<Seat> seats = new ArrayList<>();
        for (int row = 0; row < hall.getMaxRows(); row++) {
            for (int seat = 0; seat < hall.getMaxSeats(); seat++) {
                try {
                    if (hall.getSeat(row, seat).getSeatState() == seatState)
                        seats.add(hall.getSeat(row, seat));
                }catch (SeatException e){
                    e.printStackTrace();
                }
            }
        }
        return seats;
    }
}
