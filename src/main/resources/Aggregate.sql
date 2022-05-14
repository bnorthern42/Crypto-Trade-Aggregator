
--- Aggregate by Symbol note that fees are from the base currency that is traded
SELECT symbol,
	sum(case when side = 'buy' then (price * size) - fee
			when side = 'sell' then (price * (-size) + fee)
			end) as pnl_less_fees,
	avg(case when side = 'buy' then (price) end) as average_buy_price,
	avg(case when side = 'sell' then (price )end) as average_sell_price,
	sum(case when side = 'buy' then (fee) end) as total_buy_fees,
	sum(case when side = 'sell' then (fee) end) as total_sell_fees
	FROM public.filled_trades
	group by symbol;

---Aggregate total PNL
SELECT
	(sum(case when side = 'buy' then (price * size) - fee
			when side = 'sell' then (price * (-size) + fee)
			end) -
	sum(case when side = 'buy' then (fee) end) -
	sum(case when side = 'sell' then (fee) end))as pnl
	FROM public.filled_trades
	group by symbol;

