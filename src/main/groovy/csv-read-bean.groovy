@Grab('org.apache.commons:commons-csv:1.2')
import org.apache.commons.csv.CSVParser
import static org.apache.commons.csv.CSVFormat.*

import java.nio.file.Paths
import groovy.json.JsonOutput

class Country {
    String iso, iso3, country, capital, population, continent, currencyCode
}

def listing = []

Paths.get('countryInfo.txt').withReader { reader ->
    CSVParser csv = new CSVParser(reader, DEFAULT.withHeader())
    csv.iterator().each { record ->
        listing << ([iso: record.ISO, 
             iso3: record.ISO3,
             country: record.Country, 
             capital: record.Capital, 
             population: record.Population, 
             continent: record.Continent, 
             currencyCode: record.CurrencyCode] as Country)
        }
    }


for (country in listing) {
    println country.dump()
}