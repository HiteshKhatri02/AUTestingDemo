package com.example.ranosys.autestingdemo.data.car

/**
 * @author Hitesh Khatri
 */
data class Car(val name:String,
               val brand:String,
               val model:String,
               val amount:Int,
               val currency:String,
               val isPurchased:Boolean) {

    override fun toString(): String {
        return "Car(name='$name', brand='$brand', model='$model', amount=$amount, currency='$currency', isPurchased=$isPurchased)"
    }
}