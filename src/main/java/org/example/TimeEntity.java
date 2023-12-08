package org.example;

import lombok.Data;

@Data
public class TimeEntity {
    private String time;
    private String day;

    @Override
    public String toString() {
        return "Время = " + time + '\'' +
                ", День = '" + day + '\'' +
                '}';
    }
}
