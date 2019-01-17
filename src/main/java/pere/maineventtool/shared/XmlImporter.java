package pere.maineventtool.shared;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class XmlImporter<T> {
    private T data;

    public XmlImporter() {}

    public XmlImporter(T data) {
        this.data = data;
    }

    public T importFromInputStream(InputStream inputStream, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller marshaller = context.createUnmarshaller();
        return (T) marshaller.unmarshal(inputStream);
    }

    public ByteArrayOutputStream exportToFile() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(data.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        marshaller.marshal(data, output);

        return output;
    }
}
