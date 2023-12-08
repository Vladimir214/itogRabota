package org.example;

import lombok.Data;

@Data
public class RouteEntity {
private String route;
private String stop;
private String place;

    @Override
    public String toString() {
        return "Маршрут = '" + route + '\'' +
                ", Остановки по пути = '" + stop + '\'' +
                ", Место = '" + place + '\'' +
                '}';
    }
}
