package net.northern.tradeagg.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.northern.tradeagg.Util.CSVHelper;
import net.northern.tradeagg.models.Trades;
import net.northern.tradeagg.repositories.TradesRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Service
public class TradeService {

    @Autowired
    private TradesRepository repository;


    private String CSVInput;

    public TradeService(String CSVInput) {
        this.CSVInput = CSVInput;
    }



    public String save(MultipartFile file) {
        try {
            List<Trades> data = CSVHelper.parseData(file.getInputStream());
            repository.saveAll(data);
            System.out.println("SUCCESS");
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
        return "Success!";
    }

    }

