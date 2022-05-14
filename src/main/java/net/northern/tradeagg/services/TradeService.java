package net.northern.tradeagg.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.northern.tradeagg.Util.CSVHelper;
import net.northern.tradeagg.models.AggregateSymbolDTO;
import net.northern.tradeagg.models.Trades;
import net.northern.tradeagg.repositories.TradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

//TODO Make status messages use ENUM for use in multiple locations have the same status messages
    //make status messages useful (nullpointerexception) is 'nearly' usesless. for example.
    /**
     * Saves CSV File to the database via TradesRepository.java
     * @param file
     * @return String Status Message
     * */
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

    /**
     * @return List of AggregateSymbolDTO
     * if the list from the TradeRepository is null or empty, throws runtime exception
     *  then returns empty list
     * **/
    public List<AggregateSymbolDTO> GetAggregationGroupedBySymbol(){
        List<AggregateSymbolDTO> aggregateSymbolsList = new ArrayList<>();
        try {
            aggregateSymbolsList = repository.AggregateBySymbol();
            if(aggregateSymbolsList == null)
                throw new RuntimeException("Aggregation List is NULL or Empty");
            return aggregateSymbolsList;
        } catch (RuntimeException e) {
            System.out.println("Exception Thrown: " + e.getMessage());
            e.printStackTrace();
            return aggregateSymbolsList;
        }


    }

    }

