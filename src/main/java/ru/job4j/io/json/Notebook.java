package ru.job4j.io.json;

import com.google.gson.Gson;
import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "notebook")
@XmlAccessorType(XmlAccessType.FIELD)
public class Notebook {

    @XmlAttribute
    private boolean lastModel;
    @XmlAttribute
    private int price;
    @XmlAttribute
    private String model;
    private Suppliers suppliers;

    @XmlElementWrapper(name = "characteristics")
    @XmlElement(name = "characteristic")
    private String[] characteristics;

    public Notebook(boolean lastModel, int price, String model, Suppliers suppliers, String... characteristics) {
        this.lastModel = lastModel;
        this.price = price;
        this.model = model;
        this.suppliers = suppliers;
        this.characteristics = characteristics;
    }

    public Notebook() {
    }

    @Override
    public String toString() {
        return "Notebook{"
                + "lastModel=" + lastModel
                + ", price=" + price
                + ", model='" + model + '\''
                + ", suppliers=" + suppliers
                + ", characteristics=" + Arrays.toString(characteristics)
                + '}';
    }

    public static void main(String[] args) {
        Notebook n1 = new Notebook(
                true,
                1000,
                "Apple",
                new Suppliers("Restore", "Moscow Lenina 1"),
                "diagonal 20", "white");

        Gson gson = new Gson();
        String n1Json = gson.toJson(n1);
        System.out.println(n1Json);
        System.out.println("----");

        Notebook n1Dis = gson.fromJson(n1Json, Notebook.class);
        System.out.println(n1Dis);

        final String n2Json =
                "{"
                        + "\"lastModel\":true,"
                        + "\"price\":1000,"
                        + "\"model\":Apple,"
                        + "\"suppliers\":"
                        + "{"
                        + "\"name\":\"Restore\","
                        + "\"address\":\"Moscow Lenina 1\""
                        + "},"
                        + "\"characteristics\":"
                        + "[\"diagonal 20\",\"white\"]"
                        + "}";

        Notebook n2 = gson.fromJson(n2Json, Notebook.class);
        System.out.println(n2);

    }

}

@XmlRootElement(name = "suppliers")
class Suppliers {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private String address;

    public Suppliers(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Suppliers() {
    }

    @Override
    public String toString() {
        return "Suppliers{"
                + "name='" + name + '\''
                + ", address='" + address + '\''
                + '}';
    }
}
