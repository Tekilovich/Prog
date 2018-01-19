package lesson2.task1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Train {
    private int trainNo;
    private String trainSource;
    private String destination;
    private Calendar departure = new GregorianCalendar();
    private Calendar arrival = new GregorianCalendar();

    public Train(int trainNo, String trainSource, String destination, Calendar departure) {
        this.trainNo = trainNo;
        this.trainSource = trainSource;
        this.destination = destination;
        this.departure = departure;
    }

    public Train(int trainNo, String trainSource, String destination, String departure) {
        this.trainNo = trainNo;
        this.trainSource = trainSource;
        this.destination = destination;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy hh:mm");
        try {
            this.departure.setTime(dateFormat.parse(departure));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void printInformation() {

        System.out.printf("Номер поезда: %s\n", this.trainNo);
        System.out.printf("Маршрут: %s-%s\n", this.trainSource, this.destination);
        System.out.printf("Время отправления: %s\n", this.departure.getTime());

    }

    public int getTrainNo() {
        return trainNo;
    }

    public String getTrainSource() {
        return trainSource;
    }

    public String getDestination() {
        return destination;
    }

    public Calendar getDeparture() {
        return departure;
    }

    public Calendar getArrival() {
        return arrival;
    }
}

