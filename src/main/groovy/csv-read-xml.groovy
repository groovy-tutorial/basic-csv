@Grab('org.apache.commons:commons-csv:1.2')
import org.apache.commons.csv.CSVParser
import static org.apache.commons.csv.CSVFormat.*

import java.nio.file.Paths

Node top = new Node(null,'countries')

Paths.get('countryInfo.txt').withReader { reader ->
    CSVParser csv = new CSVParser(reader, DEFAULT.withHeader())
    
    for (record in csv.iterator()) {
        //This next line would create one node per country with fields as XML properties:
        //Node n = new Node(top, 'country', record.toMap(), '')
        
        Node countryNode = new Node(top, 'country')
        for (field in record.toMap()) {
            new Node(countryNode, field.key.replaceAll(/\W/, '_'), field.value)
        }
    }
}

Paths.get('countryInfo.xml').withPrintWriter { writer ->
    XmlNodePrinter xPrinter = new XmlNodePrinter(writer)
    writer.println '<?xml version="1.0"?>'
    xPrinter.print(top)
}