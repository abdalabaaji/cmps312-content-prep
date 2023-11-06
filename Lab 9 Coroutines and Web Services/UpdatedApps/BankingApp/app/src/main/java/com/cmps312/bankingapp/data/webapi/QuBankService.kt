package com.cmps312.bankingapp.data.webapi

import com.cmps312.bankingapp.data.model.Account
import com.cmps312.bankingapp.data.model.BankService
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.data.model.Transfer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class QuBankService : BankService {

    private val baseUrl = "https://cmps312banking.cyclic.app/api"
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }
            )
        }
        //Log HTTP request/response details for debugging
//        install(Logging) { level = LogLevel.BODY }
    }

    override suspend fun getTransfers(cid: Int): List<Transfer> {
        val url = "$baseUrl/transfers/$cid"
        return client.get(url).body()
    }

    override suspend fun addTransfer(transfer: Transfer): Transfer {
        val url = "$baseUrl/transfers/${transfer.cid}"
        return client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(transfer)
        }.body()
    }

    override suspend fun deleteTransfer(cid: Int, transferId: String): String {
        val url = "$baseUrl/transfers/$cid/$transferId"
        return client.delete(url).body<String>()
    }

    override suspend fun getAccounts(cid: Int): List<Account> {
        val url = "$baseUrl/accounts/$cid"
        return client.get(url).body()
    }

    override suspend fun getBeneficiaries(cid: Int): List<Beneficiary> {
        return client.get("$baseUrl/beneficiaries/$cid").body()
    }

    override suspend fun updateBeneficiary(cid: Int, beneficiary: Beneficiary): Beneficiary {
        val url = "$baseUrl/beneficiaries"
        val response = client.put(url) {
            contentType(ContentType.Application.Json)
            setBody(beneficiary)
        }.body<Beneficiary>()

        return response
    }

    override suspend fun addBeneficiary(cid: Int, beneficiary: Beneficiary): String {
        val url = "$baseUrl/beneficiaries"
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(beneficiary)
        }.body<String>()

        return response
    }

    override suspend fun deleteBeneficiary(cid: Int, accountNo: Int): String {
        val url = "$baseUrl/beneficiaries/$cid/$accountNo"
        return client.delete(url).body<String>()
    }
}