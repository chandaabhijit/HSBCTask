package com.hsbc.price.data;

import com.hsbc.core.eventbus.EventObject;

public class PriceDataEvent implements EventObject<PriceDataEvent> {
    private final String marketId;
    private final double bid;
    private final double ask;

    public PriceDataEvent(String marketId, double bid, double ask) {
        this.marketId = marketId;
        this.bid = bid;
        this.ask = ask;
    }

    public String getMarketId() {
        return marketId;
    }

    public double getBid() {
        return bid;
    }

    public double getAsk() {
        return ask;
    }

    @Override
    public int hashCode() {
        return marketId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof PriceDataEvent)) {
            return false;
        }

        PriceDataEvent event = (PriceDataEvent)obj;
        return marketId.compareTo(event.getMarketId()) == 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PriceDataEvent{");
        sb.append("marketId='").append(marketId).append('\'');
        sb.append(", bid=").append(bid);
        sb.append(", ask=").append(ask);
        sb.append('}');
        return sb.toString();
    }
}
