package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //gg
        Scanner scanner = new Scanner(System.in);
        ListFlights flights = new ListFlights();
        File file = new File("./file.txt");
        if (file.createNewFile()) {
            System.out.println("Файл создан");
        } else {
            System.out.println("У вас уже создан файл");
        }
        try (FileReader fileReader = new FileReader(file)) {
            Scanner fileScanner = new Scanner(fileReader);
            if (fileScanner.hasNextLine()) {
                String str2 = fileScanner.nextLine();
                Gson gson = new Gson();
                flights = gson.fromJson(str2, ListFlights.class);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int menu = 0;

        do { try{
            System.out.println("1 - Добавить рейс");
            System.out.println("2 - Вывести");
            System.out.println("3 - Поиск");
            System.out.println("4 - Удалить и после удаления перезапишите файл");
            System.out.println("5 - Записать в файл");
            System.out.println("6 - Если хотите уйти");
            menu = scanner.nextInt();
            switch(menu) {
                case 1:
                    scanner.nextLine();
                    System.out.println("После добавления перезапишите файл\n");
                    RouteEntity route = new RouteEntity();
                    System.out.println("Введите ваше место");
                    route.setPlace(scanner.nextLine());
                    System.out.println("Введите остановки");
                    route.setStop(scanner.nextLine());
                    System.out.println("Введите маршрут");
                    route.setPlace(scanner.nextLine());
                    TimeEntity time = new TimeEntity();
                    System.out.println("Введите день");
                    time.setDay(scanner.nextLine());
                    System.out.println("Введите время отправки");
                    time.setTime(scanner.nextLine());
                    FlightEntity flight = new FlightEntity();
                    flight.setRoutes(route);
                    flight.setTimer(time);
                    System.out.println("Введите название рейса");
                    flight.setFlight(scanner.nextLine());
                    System.out.println("Введите номер рейса");
                    flight.setNumber(scanner.nextLine());
                    if (flights.getData() == null) {
                        List temp = new ArrayList<>();
                        temp.add(flight);
                        flights.setData(temp);
                    } else {
                        flights.getData().add(flight);
                    }
                    break;
                case 2:
                    try {
                        flights.getData().forEach(System.out::println);
                    }catch (NullPointerException e){} break;
                case 3:
                    System.out.println("Введите данные книги которую хотите найти: ");
                    scanner.nextLine();
                    String textSearch = scanner.nextLine();
                    List searchResult = new ArrayList<>();
                    for(FlightEntity flight1: flights.getData()) {
                        if(flight1.getNumber().equals(textSearch)){
                            searchResult.add(flight1);
                        }
                        if (searchResult.isEmpty()) {
                            System.out.println("Нет такого рейса");
                            if(flight1.getFlight().equals(textSearch)){
                                searchResult.add(flight1);
                            }
                        }else {
                            searchResult.forEach(System.out::println);
                        }
                    }break;
                case 4:
                    try{
                    System.out.println("Введите название рейса для удаления");
                    scanner.nextLine();
                    String deleteBook = scanner.nextLine();
                    List deleteResult = new ArrayList<>();
                    for (FlightEntity r : flights.getData()) {
                        if (r.getNumber().equalsIgnoreCase(deleteBook)) {
                            deleteResult.add(r);
                        }
                    }
                    if (deleteResult.isEmpty()) {
                        System.out.println("Нет такого рейса");
                    } else {
                        for (Object b:deleteResult) {
                            System.out.println("Удалить?" + " Да" + " Нет\n" + b);
                            String d = scanner.nextLine();
                            if (d.equalsIgnoreCase("Да")) {
                                flights.getData().remove(b);
                                System.out.println("Успешно");
                            } else {
                                System.out.println("Отмена удаления");
                            }
                        }
                    }
            }catch (NullPointerException e){}
                    break;
                case 5:
                    Gson gson = new Gson();
                    String str = gson.toJson(flights);
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write(str);
                        System.out.println("Успешно записано");
                    }catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }catch (IOException e) {
                        throw new RuntimeException(e);
                    }break;
                case 6:
                    System.out.println("\n До свидания! ");
                    break;
            }
        } catch(InputMismatchException e) {
            System.out.println("Ошибка!\nНекорректный ввод, повторите попытку.");
            scanner.nextLine();
            String str = "1";
            menu = Integer.parseInt(str);
        }catch (NumberFormatException ignored){
        }
        }while( menu !=6);

    }
}
