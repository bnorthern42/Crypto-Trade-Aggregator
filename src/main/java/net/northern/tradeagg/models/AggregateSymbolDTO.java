package net.northern.tradeagg.models;


/**
 * Aggregated Symbol DTO.
 * Takes return from native query, see Trades.java "Trades.AggregateBySymbol"
 *
 * */
public class AggregateSymbolDTO {
    //odd bug with primitive doubles so java.lang.Double is used instead
    private String symbol;
    private Double pnl_less_fees;
    private Double average_buy_price;
    private Double average_sell_price;
    private Double total_buy_fees;
    private Double total_sell_fees;

    public AggregateSymbolDTO(String symbol, Double pnl_less_fees, Double average_buy_price, Double average_sell_price, Double total_buy_fees, Double total_sell_fees) {
        this.symbol = symbol;
        this.pnl_less_fees = pnl_less_fees;
        this.average_buy_price = average_buy_price;
        this.average_sell_price = average_sell_price;
        this.total_buy_fees = total_buy_fees;
        this.total_sell_fees = total_sell_fees;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPnl_less_fees() {
        return pnl_less_fees;
    }

    public Double getAverage_buy_price() {
        return average_buy_price;
    }

    public Double getAverage_sell_price() {
        return average_sell_price;
    }

    public Double getTotal_buy_fees() {
        return total_buy_fees;
    }

    public Double getTotal_sell_fees() {
        return total_sell_fees;
    }

    @Override
    public String toString() {
        return "AggregateSymbolDTO{" +
                "symbol='" + symbol + '\'' +
                ", pnl_less_fees='" + pnl_less_fees + '\'' +
                ", average_buy_price=" + average_buy_price +
                ", average_sell_price=" + average_sell_price +
                ", total_buy_fees=" + total_buy_fees +
                ", total_sell_fees=" + total_sell_fees +
                '}';
    }
}
