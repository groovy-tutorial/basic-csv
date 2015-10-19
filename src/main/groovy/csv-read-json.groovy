@Grab('org.apache.commons:commons-csv:1.2')
import org.apache.commons.csv.CSVParser
import static org.apache.commons.csv.CSVFormat.*

import java.nio.file.Paths
import groovy.json.JsonOutput

def listing = []

Paths.get('countryInfo.txt').withReader { reader ->
    CSVParser csv = new CSVParser(reader, DEFAULT.withHeader())

    for (record in csv.iterator()) {
        listing << record.toMap()
    }
}

Paths.get('countryInfo.json').withWriter { jsonWriter ->
    jsonWriter.write JsonOutput.prettyPrint(JsonOutput.toJson(listing))
}
