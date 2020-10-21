package automation.writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Component
public class CsvWriter {

    @Value(value = "${csv.dir}")
    private String dir;

    @Value(value = "${csv.interval.minutes}")
    private Integer interval;

    BufferedWriter writer;

    public CSVPrinter csvPrinter;

    long timeStamp = System.currentTimeMillis();

    public void writeToCsv(final Map<String, String> map) throws IOException {
        if(writer == null) {
            writer = Files.newBufferedWriter(Paths.get(dir+"/DeviceStatus."+timeStamp+".csv"));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("DeviceName", "SwitchStatus", "Timestamp", "powerConsumption"));
        }

        csvPrinter.printRecord(map.get("DeviceName"),map.get("SwitchStatus"),map.get("Timestamp"),map.get("powerConsumption"));

        if(System.currentTimeMillis()> (timeStamp + interval * 60 * 1000)) {
            timeStamp = System.currentTimeMillis();
            csvPrinter.flush();
            writer=null;
            csvPrinter=null;
        }
    }
}
