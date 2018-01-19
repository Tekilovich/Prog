package lesson2.task1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        XmlParser xml = new XmlParser("D://javaFiles//train.xml");
        ListTrains allTrains = new ListTrains(xml.importTrains());
        ArrayList<Train> trains = allTrains.getTrainsByDepartureTimeRange("1:00", "19:00");

        for (Train train : trains) {
            train.printInformation();
        }

        Train train = new Train(3,"Odessa","Kiev", "21.01.2018 16:05");
        xml.addTrain(train);

    }
}
