package lesson2.task3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "organization")
public class Organization {

    @XmlElement
    private Title title;
    private Currency currency;

    @XmlElement(name = "currencies")
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Title getTitle() {
        return title;
    }

    public Currency getCurrency() {
        return currency;
    }

    @XmlRootElement(name = "title")
    public static class Title {

        @XmlAttribute
        private String value;

        public String getValue() {
            return value;
        }
    }

}
