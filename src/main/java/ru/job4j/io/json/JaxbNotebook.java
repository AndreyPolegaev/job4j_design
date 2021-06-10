package ru.job4j.io.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxbNotebook {

    public static void main(String[] args) throws JAXBException, IOException {
        Notebook n1 = new Notebook(
                true,
                1000,
                "Apple",
                new Suppliers("Restore", "Moscow Lenina 1"),
                "diagonal 20", "white");
        JAXBContext context = JAXBContext.newInstance(Notebook.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(n1, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Notebook notebook = (Notebook) unmarshaller.unmarshal(reader);
            System.out.println(notebook);
        }
    }
}
