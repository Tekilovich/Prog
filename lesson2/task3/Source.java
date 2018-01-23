package lesson2.task3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "source")
public class Source {

    private String id;

    @XmlElement
    private Organizations organizations;

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public List<Organization> getOrganizations() {
        return organizations.organizations;
    }
}
