package com.npes87184.pokeresearchdictionary.Utils

import android.app.Activity
import com.android.billingclient.api.*

class BillingManager(val activity : Activity, val billingUpdatesListener: BillingUpdatesListener) : PurchasesUpdatedListener {
    var billingClient : BillingClient? = null

    init {
        billingClient = BillingClient.newBuilder(activity).setListener(this).build()
    }

    override fun onPurchasesUpdated (responseCode: Int, purchases: List<Purchase>?) {
        if (responseCode == BillingClient.BillingResponse.OK) {
            for (purchase in purchases!!) {
                consumePurchase(purchase)
            }
        }
    }

    private fun startServiceConnectionIfNeeded(executeOnSuccess: Runnable?) {
        if (billingClient!!.isReady) {
            executeOnSuccess?.run()
        } else {
            billingClient!!.startConnection(object : BillingClientStateListener {
                override fun onBillingSetupFinished(@BillingClient.BillingResponse billingResponse: Int) {
                    if (billingResponse == BillingClient.BillingResponse.OK) {
                        executeOnSuccess?.run()
                    }
                }
                override fun onBillingServiceDisconnected() {
                }
            })
        }
    }

    fun launchPurchaseFlow(skuId: String) {
        val billingFlowParams = BillingFlowParams.newBuilder().setType(BillingClient.SkuType.INAPP).setSku(skuId).build()
        val purchaseFlowRunnable = Runnable {
            billingClient!!.launchBillingFlow(activity, billingFlowParams)
        }
        startServiceConnectionIfNeeded(purchaseFlowRunnable)
    }

    fun destroyBillingClient() {
        billingClient!!.endConnection()
    }

    fun consumePurchase(purchase: Purchase) {
        val consumePurchaseRunnable = Runnable {
            billingClient!!.consumeAsync(purchase.purchaseToken) { responseCode, purchaseToken ->
                if (responseCode == BillingClient.BillingResponse.OK){
                    billingUpdatesListener.onConsumeFinished(purchaseToken, responseCode)
                }
            }
        }
        startServiceConnectionIfNeeded(consumePurchaseRunnable)
    }

    interface BillingUpdatesListener {
        fun onConsumeFinished(token: String, @BillingClient.BillingResponse responseCode: Int)
    }
}