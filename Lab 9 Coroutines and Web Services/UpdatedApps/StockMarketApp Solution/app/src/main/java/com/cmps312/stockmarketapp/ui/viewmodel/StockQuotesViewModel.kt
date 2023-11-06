package com.cmps312.stockmarketapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.stockmarketapp.data.model.StockQuote
import com.cmps312.stockmarketapp.data.webapi.SimulatedStockQuoteService
import com.cmps312.stockmarketapp.ui.screens.components.removeTrailingComma
import kotlinx.coroutines.*

class StockQuotesViewModel : ViewModel() {
    private val stockQuoteService = SimulatedStockQuoteService()

    var selectedCompanies by mutableStateOf("Tesla, Apple, Microsoft, IBM,")

    var companyStockQuotes = mutableStateListOf<StockQuote>()
    var runJobsInParallel by mutableStateOf(true)

    var jobStatusGetStockQuotes by mutableStateOf(JobState.SUCCESS)
    var executionDuration by mutableStateOf(0L)
    var errorMessage by mutableStateOf("")

    private suspend fun getStockQuotesSequential(companies: List<String>) {
        // ToDo: Implement this method
//        throw NotImplementedError( "Implement this method.")
        /*
        This method should get the stock quote for each company and add the received quote to companyStockQuotes list.
         */
        for (company in companies) {
            val quote = stockQuoteService.getStockQuote(company)
            companyStockQuotes.add(quote)
        }
    }

    suspend fun getStockQuotesInParallel(companies: List<String>) = withContext(Dispatchers.IO) {
        companies.map { async { stockQuoteService.getStockSymbol(it) } }
            .map { it.await() }
    }

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        errorMessage = exception.message ?: "Request failed."
        jobStatusGetStockQuotes = JobState.CANCELLED
        println(">>> Debug: $errorMessage")
    }

    fun getStockQuotes() {
        // ToDo: Implement this method
//        throw NotImplementedError("Implement this method.")
//        return stockQuoteService.getStockQuote(name)

        val startTime = System.currentTimeMillis()
        executionDuration = 0L
        errorMessage = ""
        companyStockQuotes.clear()

        val companiesList = selectedCompanies.trim().removeTrailingComma().split(",")

        val job =
            viewModelScope.launch(exceptionHandler) {
                if (runJobsInParallel) {
                    val quotes = getStockQuotesInParallel(companiesList).map {
                        stockQuoteService.getStockQuote(it)
                    }
                    companyStockQuotes.addAll(quotes)
                } else {
                    getStockQuotesSequential(companiesList)
                }
            }

        job.invokeOnCompletion {
            if (!job.isCancelled) {
                executionDuration = (System.currentTimeMillis() - startTime) / 1000
                println(">>> Debug: Job done. Total execution time: ${executionDuration}s")
            }
        }
    }
}