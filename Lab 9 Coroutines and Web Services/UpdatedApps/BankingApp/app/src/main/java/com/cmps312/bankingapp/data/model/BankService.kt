package com.cmps312.bankingapp.data.model

import com.cmps312.bankingapp.data.model.Account
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.data.model.Transfer

interface BankService {
    suspend fun getTransfers(cid: Int) : List<Transfer>
    suspend fun addTransfer(transfer: Transfer) : Transfer
    suspend fun deleteTransfer(cid: Int , transferId : String) : String

//    accounts
    suspend fun getAccounts(cid: Int) : List<Account>
    suspend fun getBeneficiaries(cid: Int) : List<Beneficiary>
    suspend fun updateBeneficiary(cid: Int, beneficiary: Beneficiary) : Beneficiary
    suspend fun addBeneficiary(cid: Int, beneficiary: Beneficiary) : String
    suspend fun deleteBeneficiary(cid: Int, accountNo: Int) : String
}