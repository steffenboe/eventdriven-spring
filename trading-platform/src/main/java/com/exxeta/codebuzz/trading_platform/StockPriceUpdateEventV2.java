package com.exxeta.codebuzz.trading_platform;

import java.io.Serializable;

record StockPriceUpdateEventV2(Symbol symbol, double priceInCents) {  
}
// record StockPriceUpdateEvent(String version, String symbol, double priceInCents, Boolean flagged) {
    
// }
