package com.example.ranosys.autestingdemo.data.car

import android.support.annotation.Nullable
import com.example.ranosys.autestingdemo.utils.AppConstants
import java.util.*

/**
 * @author Hitesh Khatri
 */
data class Car(
        val id:String,
        val name:String,
        val brand:String,
        val model:String,
        val amount:Int,
        val currency:String,
        val isPurchased:Boolean) {




    fun Car(name: String,brand: String, model: String){

        Car(UUID.randomUUID().toString(),name,brand,model,amount,AppConstants.CURRENCY_IN_DOLLAR,false)
    }


    fun Car(name: String,brand: String, model: String,purchased:Boolean){

        Car(UUID.randomUUID().toString(),name,brand,model,amount,AppConstants.CURRENCY_IN_DOLLAR,purchased)
    }

    fun Car(id:String, name: String,brand: String, model: String){

        Car(id,name,brand,model,amount,AppConstants.CURRENCY_IN_DOLLAR,false)
    }


    override fun toString(): String {
        return "Car(name='$name', brand='$brand', model='$model', amount=$amount, currency='$currency', isPurchased=$isPurchased)"
    }
}