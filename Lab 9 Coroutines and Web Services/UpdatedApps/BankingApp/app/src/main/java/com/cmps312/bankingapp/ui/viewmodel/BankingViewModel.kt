package com.cmps312.bankingapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.bankingapp.data.model.Account
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.data.model.Transfer
import com.cmps312.bankingapp.data.webapi.QuBankService
import kotlinx.coroutines.launch

class BankingViewModel(appContext: Application) : AndroidViewModel(appContext) {
    private val TAG = "TransferViewModel"
    private val quBankService = QuBankService()

    val accounts = mutableStateListOf<Account>()
    val beneficiaries = mutableStateListOf<Beneficiary>()

    //it will automatically recompose the UI once the data is received
    var transfers = mutableStateListOf<Transfer>()

    init {
        viewModelScope.launch {
            getTransfers()
        }
    }

    private suspend fun getTransfers() {
        Log.d("Transfers", "getTransfers: ")
        viewModelScope.launch {
            transfers.addAll(quBankService.getTransfers(10001))
        }
    }

    // used for holding the details of new Transfer - used instead of Nav Component nav args
    lateinit var newTransfer: Transfer


    //Fund Transfer screen calls this method before naviagation
    fun setTransferFromDetails(fromAccount: String, amount: Double) {
        newTransfer = Transfer(fromAccount, amount)
    }

    fun setTransferBeneficiaryDetails(beneficiaryName: String, beneficiaryAccountNo: String) {
        newTransfer.beneficiaryName = beneficiaryName
        newTransfer.beneficiaryAccountNo = beneficiaryAccountNo
    }

    fun getAccounts() = viewModelScope.launch {
        accounts.addAll(quBankService.getAccounts(10001))
    }

    fun addTransfer(transfer: Transfer) {
        viewModelScope.launch {
            val newTransfer = quBankService.addTransfer(transfer)
            transfers?.add(newTransfer)
        }
    }

    fun getTransfer(transferId: String) = transfers.find { it.transferId == transferId }

    fun getAccount(accountNo: String): Account? = accounts.find { it.accountNo == accountNo }

    fun getBeneficiaries() {
        TODO("Not yet implemented")
        viewModelScope.launch {
            beneficiaries.addAll(quBankService.getBeneficiaries(10001))
        }
    }

    fun deleteTransfer(transferId: String) {
//        TODO("Not yet implemented")
        transfers.removeIf { transferId == it.transferId }
        viewModelScope.launch {
            quBankService.deleteTransfer(10001, transferId)
        }
    }
}


