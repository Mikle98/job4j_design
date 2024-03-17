package ru.job4j.io.serialization.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        final MyClass myClass = new MyClass(10, true, "qwer", new MyClass2(0, "00"), new int[] {1, 2, 3});
        JAXBContext context = JAXBContext.newInstance(MyClass.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(myClass, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            MyClass rsl = (MyClass) unmarshaller.unmarshal(reader);
            System.out.println(rsl);
        }
    }
}
