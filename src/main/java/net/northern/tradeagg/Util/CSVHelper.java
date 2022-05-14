package net.northern.tradeagg.Util;

import com.google.common.collect.Iterables;
import net.northern.tradeagg.models.Trades;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"tradeCreatedAt","orderId","symbol", "side","price","size","funds","fee","liquidity","feeCurrency","orderType"};
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            System.out.println("file no accepted");
            System.out.println(file.getContentType());
            return false;
        }
        System.out.println("file accepted");
        System.out.println(file.getContentType());
        return true;
    }


    public static List<Trades> parseData(InputStream inputStream) {
// CSVFormat.DEFAULT
//                     .withHeader("tradeCreatedAt","orderId","symbol", "side","price","size","funds","fee","liquidity","feeCurrency","orderType")
//                     .withIgnoreHeaderCase()
//                     .withTrim().withDelimiter(',')
//CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,CSVFormat.EXCEL
                     .withFirstRecordAsHeader());
             ) {

            List<Trades> mytrades = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            //Iterables.skip(csvRecords,1)
            for (CSVRecord csvRecord : csvRecords) {


              //  System.out.println(ZonedDateTime.parse(csvRecord.get("tradeCreatedAt")).toString());
                String tradeCreatedAt = csvRecord.get(0);
                Timestamp tradeCreatedAtTs = new Timestamp((dateFormat.parse(tradeCreatedAt)).getTime());

                Trades trades = new Trades(

                        tradeCreatedAtTs,
                        csvRecord.get(1),
                        csvRecord.get(2),
                        csvRecord.get(3),
                        Double.parseDouble(csvRecord.get(4)),
                        Double.parseDouble(csvRecord.get(5)),
                        Double.parseDouble(csvRecord.get(6)),
                        Double.parseDouble(csvRecord.get(7)),
                        csvRecord.get(8),
                        csvRecord.get(9),
                        csvRecord.get(10)
                );

                try {
                    mytrades.add(trades);

                }catch (Exception e){
                    System.out.println("could not add trade" + e.getMessage());
                }


            }
            return mytrades;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());

        }
    }
}
