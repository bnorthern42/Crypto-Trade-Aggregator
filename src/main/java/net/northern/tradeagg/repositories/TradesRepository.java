package net.northern.tradeagg.repositories;


import net.northern.tradeagg.models.AggregateSymbolDTO;
import net.northern.tradeagg.models.Trades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradesRepository extends JpaRepository<Trades, String> {


        List<Trades> findAllBySymbol(String symbol);

        /**
         * Returns Aggregated query Data
         * @return List of AggregateSymbolDTO
         * */
        @Query(  nativeQuery = true)
        List<AggregateSymbolDTO> AggregateBySymbol();


}
