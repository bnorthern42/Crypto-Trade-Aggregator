package net.northern.tradeagg.models;

import lombok.*;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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
