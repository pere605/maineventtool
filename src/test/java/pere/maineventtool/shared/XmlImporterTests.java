package pere.maineventtool.shared;

import lombok.SneakyThrows;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pere.maineventtool.domain.seasonalevent.xml.SeasonalEventXml;
import pere.maineventtool.domain.seasonalevent.xml.SeasonalEventsXml;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class XmlImporterTests {
    @Test
    @SneakyThrows
    public void testImport() {
        XmlImporter xmlImporter = new XmlImporter();
        byte[] data = ("<events>" +
                "<event>" +
                "<id>1</id>" +
                "<type>tournament</type>" +
                "<sub_type>steel</sub_type>" +
                "<start>2016-02-23T19:00:00+00:00</start>" +
                "<end>2016-02-23T19:00:00+00:00</end>" +
                "<name>TOURNAMENTS|Planks Tournament</name>" +
            "</event></events>").getBytes();

        MultipartFile file = new MockMultipartFile(
                "data",
                "test.xml",
                "text/plain",
                data
        );

        SeasonalEventsXml xml = (SeasonalEventsXml) xmlImporter.importFromInputStream(file.getInputStream(), SeasonalEventsXml.class);
        assertThat(xml.events, instanceOf(List.class));
        SeasonalEventXml element = xml.events.get(0);
        assertEquals(xml.events.size(), 1);
        assertEquals(element.id, "1");
        assertEquals(element.name, "TOURNAMENTS|Planks Tournament");
        assertEquals(element.type, "tournament");
        assertEquals(element.subType, "steel");
        assertEquals(element.start, "2016-02-23T19:00:00+00:00");
        assertEquals(element.end, "2016-02-23T19:00:00+00:00");
    }

    @Test
    @SneakyThrows
    public void testExport() {
        SeasonalEventsXml xml = new SeasonalEventsXml();
        SeasonalEventXml element = new SeasonalEventXml();
        element.id = "1";
        element.name = "Some tournament";
        element.type = "tournament";
        element.subType = "steel";
        element.start = "2016-02-23T19:00:00+00:00";
        element.end = "2016-02-23T19:00:00+00:00";

        xml.events = List.of(element);
        XmlImporter xmlImporter = new XmlImporter(xml);

        String output = xmlImporter.exportToFile().toString();
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<events>\n" +
                "    <event>\n" +
                "        <id>1</id>\n" +
                "        <name>Some tournament</name>\n" +
                "        <type>tournament</type>\n" +
                "        <sub_type>steel</sub_type>\n" +
                "        <start>2016-02-23T19:00:00+00:00</start>\n" +
                "        <end>2016-02-23T19:00:00+00:00</end>\n" +
                "    </event>\n" +
                "</events>\n";

        assertEquals(expected, output);
    }
}
