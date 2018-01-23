package lesson2.task3;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        File file = new File("lesson2/task3/currency-cash.xml");
        Source source = new Source();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Source.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            source = (Source) unmarshaller.unmarshal(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println(source.getOrganizations().get(0).getCurrency().getC().get(0).getAr());

    }
}
