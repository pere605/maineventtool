package pere.maineventtool.domain.seasonalevent.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class XmlImporter {
    public static Object importFromInputStream(InputStream inputStream, Class importType) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(importType);
        Unmarshaller marshaller = context.createUnmarshaller();
        return marshaller.unmarshal(inputStream);
    }
}
