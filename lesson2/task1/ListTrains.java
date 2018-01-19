package lesson2.task1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ListTrains {

    ArrayList<Train> trains = new ArrayList<Train>();

    public ListTrains(ArrayList<Train> trains) {
        this.trains = trains;
    }

    public ArrayList<Train> getAllTrains() {
        return trains;
    }

    public ArrayList<Train> getTrainsByDepartureTimeRange(String timeFrom, String timeTo) {

        ArrayList<Train> trains = new ArrayList<Train>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        GregorianCalendar from = new GregorianCalendar();
        try {
            from.setTime(dateFormat.parse(timeFrom));
        } catch (ParseException e) {
            System.out.println("Ошибка формата времени!/nПример ввода времени: 19:05");
        }
        GregorianCalendar to = new GregorianCalendar();
        try {
            to.setTime(dateFormat.parse(timeTo));
        } catch (ParseException e) {
            System.out.println("Ошибка формата времени!/nПример ввода времени: 19:05");
        }

        for (Train train : this.trains) {

            Calendar departure = train.getDeparture();

            if (timeToInt(departure) >= timeToInt(from) && timeToInt(departure) <= timeToInt(to)) {
                trains.add(train);
            }
        }
        return trains;
    }

    public void print () {

        for (Train train : trains) {
            train.printInformation();
        }

        System.out.println("-------------------------------------------------");
    }

    private int timeToInt(Calendar time) {

        return time.get(Calendar.HOUR_OF_DAY)*60 + time.get(Calendar.MINUTE);

    }
}
