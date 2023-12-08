package org.example;

import lombok.Data;

import java.util.List;

@Data
public class ListFlights {
    private List<FlightEntity> data;

    @Override
    public String toString() {
        return "Лист рейсов{" +
                "data=" + data +
                '}';
    }
}