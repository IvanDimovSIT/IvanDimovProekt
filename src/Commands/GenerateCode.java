package Commands;

import com.company.Seat;

import java.time.LocalDate;

public interface GenerateCode {
    String getCode(Seat seat, int hallNumber, LocalDate date);
}
