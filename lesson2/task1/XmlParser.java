package lesson2.task1;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class XmlParser {

    private File file;
    private Document document;
    private ArrayList<Train> trains;

    public XmlParser(String path) {

        this.file = new File(path);
        parse(file);

    }

    private void parse(File file) {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Train> importTrains() {

        trains = new ArrayList<Train>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy hh:mm");

        Element rootElement = document.getDocumentElement();

        NodeList nodeListTrains = rootElement.getChildNodes();

        for (int i = 0; i < nodeListTrains.getLength(); i++) {
            Node nodeTrain = nodeListTrains.item(i);

            if (nodeTrain.getNodeType() == Node.ELEMENT_NODE) {
                Element elementTrain = (Element) nodeTrain;

                //getting the train number from file
                int trainNo = Integer.parseInt(nodeTrain.getAttributes().getNamedItem("id").getNodeValue());
                //getting the source station from file
                String trainSource = elementTrain.getElementsByTagName("from").item(0).
                        getChildNodes().item(0).getNodeValue();
                //getting the finish station from file
                String destination = elementTrain.getElementsByTagName("to").item(0).
                        getChildNodes().item(0).getNodeValue();
                //getting the date and the time of train departure
                String date = elementTrain.getElementsByTagName("date").item(0).
                        getChildNodes().item(0).getNodeValue();
                String time = elementTrain.getElementsByTagName("departure").item(0).
                        getChildNodes().item(0).getNodeValue();
                GregorianCalendar departure = new GregorianCalendar();
                try {
                    departure.setTime(dateFormat.parse(String.format("%s %s", date, time)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                trains.add(new Train(trainNo,trainSource,destination,departure));
            }
        }
        return trains;
    }

    public void addTrain (Train train) {

        if (trains.isEmpty()) importTrains();

        Element rootElement = document.getDocumentElement();

        Element elementTrain = document.createElement("train");
        elementTrain.setAttribute("id", String.valueOf(train.getTrainNo()));
        rootElement.appendChild(elementTrain);
        Element elementFrom = document.createElement("from");
        elementFrom.setTextContent(train.getTrainSource());
        elementTrain.appendChild(elementFrom);
        Element elementTo = document.createElement("to");
        elementTo.setTextContent(train.getDestination());
        elementTrain.appendChild(elementTo);
        Element elementDate = document.createElement("date");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        elementDate.setTextContent(dateFormat.format(train.getDeparture().getTime()));
        elementTrain.appendChild(elementDate);
        Element elementDeparture = document.createElement("departure");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        elementDeparture.setTextContent(timeFormat.format(train.getDeparture().getTime()));
        elementTrain.appendChild(elementDeparture);


        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(file);

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(domSource,result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}