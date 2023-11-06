package com.cmps312.stockmarketapp.data.webapi

import com.cmps312.stockmarketapp.data.model.StockQuote
import kotlinx.coroutines.delay

class SimulatedStockQuoteService {
    private val companies = mapOf(
        "Apple" to "AAPL",
        "Amazon" to "AMZN",
        "Alibaba" to "BABA",
        "Salesforce" to "CRM",
        "Facebook" to "FB",
        "Google" to "GOOGL",
        "IBM" to "IBM",
        "Johnson & Johnson" to "JNJ",
        "Microsoft" to "MSFT",
        "Tesla" to "TSLA"
    )

    suspend fun getStockSymbol(name: String): String {
        // ToDo: Implement this method
//        throw NotImplementedError( "Implement this method.")
        delay(1000)
        return companies.getValue(name)

    }

    suspend fun getPrice(symbol: String): Int {
        // ToDo: Implement this method
//        throw NotImplementedError("Implement this method.")
        delay(1000)
        return (50..500).random()
    }

    suspend fun getStockQuote(name: String): StockQuote {
        // ToDo: Implement this method
        // Tip: use withContext(Dispatchers.IO) to run multiple coroutines in the IO thread
        //throw NotImplementedError("Implement this method.")
        delay(5000)
        return StockQuote(name, getStockSymbol(name), getPrice(getStockSymbol(name)))
    }

    suspend fun getCompanies(): List<String> {
        // ToDo: Implement this method
//        throw NotImplementedError("Implement this method.")
        delay(1000)
        return companies.keys.toList()
    }
}