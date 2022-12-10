package com.example.Helpers.Travels;

import java.time.LocalDate;
import java.util.List;

public class UnsoldTicketsNotification {

    public static int unsoldTicketsNotification(LocalDate currentTime, List<LocalDate> datesWithUnsoldTickets){
        LocalDate tomorrow = currentTime.plusDays(1);
        int count = 0;
        for(LocalDate t : datesWithUnsoldTickets){
            if(tomorrow.equals(t)){
                count++;
            }
        }
        return count;
    }

}
