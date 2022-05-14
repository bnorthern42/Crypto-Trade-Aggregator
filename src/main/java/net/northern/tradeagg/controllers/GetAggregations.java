package net.northern.tradeagg.controllers;

import net.northern.tradeagg.models.AggregateSymbolDTO;
import net.northern.tradeagg.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class GetAggregations {

    @Autowired
    TradeService tradeService;

    @GetMapping("/aggKucoinSymbol")
    public List<AggregateSymbolDTO> aggregateSymbols(){
        return tradeService.GetAggregationGroupedBySymbol();

    }


}
