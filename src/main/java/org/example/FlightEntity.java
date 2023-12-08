package org.example;

import lombok.Data;

@Data
public class FlightEntity {
    private String number;
    private RouteEntity routes;
    private TimeEntity timer;
    private String flight;

    @Override
    public String toString() {
        return "Рейс{" +
                "Номер = '" + number +'\'' + routes + '\''+ "Дата и время:{ " +  timer +
                ", Рейс = '" + flight + '\'' +
                '}';
    }
}
