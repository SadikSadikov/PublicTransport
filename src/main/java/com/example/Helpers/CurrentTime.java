package com.example.Helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {
    public static LocalDate getTime() {
        return LocalDate.now();
    }
}
