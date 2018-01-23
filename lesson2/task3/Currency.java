package lesson2.task3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "currencies")
public class Currency {

    @XmlElement
    private List<C> c = new ArrayList<>();



    public List<C> getC() {
        return c;
    }

    @XmlRootElement(name = "c")
    public static class C {

        @XmlAttribute
        private String id;
        @XmlAttribute
        private String br;
        @XmlAttribute
        private String ar;


        public String getId() {
            return id;
        }

        public String getBr() {
            return br;
        }

        public String getAr() {
            return ar;
        }
    }

}
