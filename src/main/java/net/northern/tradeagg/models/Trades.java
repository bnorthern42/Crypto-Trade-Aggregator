package net.northern.tradeagg.models;

import lombok.*;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import javax.persistence.*;
import java.sql.Timestamp;

// Thanks to: https://thorben-janssen.com/result-set-mapping-constructor-result-mappings/

/**
 * Filled Trades Class
 *
 * NamedNativeQuery:
 *   Returns each symbol PNL minus Fees for base currency,
 *   average buy price for that symbol
 *   average sell price for that symbol
 *   total buy fees in base currency
 *   total sell fees in base currency
 * */
@NamedNativeQuery(name = "Trades.AggregateBySymbol",
                    query=  "SELECT symbol, " +
                            " sum(case when side = 'buy' then (price * size) - fee " +
                            "   when side = 'sell' then (price * (-size) + fee) " +
                            "   end) as pnl_less_fees, " +
                            " avg(case when side = 'buy' then (price) end) as average_buy_price, " +
                            " avg(case when side = 'sell' then (price )end) as average_sell_price, " +
                            " sum(case when side = 'buy' then (fee) end) as total_buy_fees, " +
                            " sum(case when side = 'sell' then (fee) end) as total_sell_fees " +
                            " FROM filled_trades " +
                            " group by symbol",
                    resultSetMapping =  "Mapping.AggregateSymbolDTO")
@SqlResultSetMapping(name =  "Mapping.AggregateSymbolDTO",
                     classes =  @ConstructorResult(targetClass = AggregateSymbolDTO.class,
                                                    columns = { @ColumnResult(name = "symbol",type = StringType.class),
                                                                @ColumnResult(name = "pnl_less_fees", type = DoubleType.class),
                                                                @ColumnResult(name = "average_buy_price", type = DoubleType.class),
                                                                @ColumnResult(name = "average_sell_price", type = DoubleType.class),
                                                                @ColumnResult(name = "total_buy_fees", type = DoubleType.class),
                                                                @ColumnResult(name = "total_sell_fees", type = DoubleType.class)})) //odd bug with primitive doubles
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FilledTrades")
public class Trades {




    @Column(name ="tradeCreatedAt")
    private Timestamp localDateTime;

    @Id
    @Column(name = "orderId")
    private String orderId;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "side")
    private String side;

    @Column(name = "price")
    private double price;

    @Column(name = "size")
    private double size;

    @Column(name = "funds")
    private double funds;

    @Column(name = "fee")
    private double fee;

    @Column(name = "liquidity")
    private String liquidity;

    @Column(name = "feeCurrency")
    private String feeCurrency;

    @Column(name = "orderType")
    private String orderType;





}
