package Commands;

import com.company.Hall;
import com.company.Seat;
import com.company.SeatState;

import java.util.List;

public interface FindSeats {
    List<Seat> findSeats(Hall hall, SeatState seatState);
}
