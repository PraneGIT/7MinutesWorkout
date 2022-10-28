package com.example.a7minuteworkout

import android.media.Image

class ExcerciseModel (
     private var id:Int,
     private var  name:String,
     private var image:Int,
     private var  isCompleted:Boolean,
     private var isSeleceted:Boolean
){
    fun getId():Int{
        return id
    }
    fun setId(id:Int){
        this.id=id
    }
    fun getname():String{
        return name
    }
    fun setname(name:String){
        this.name=name
    }

    fun getImage():Int{
        return image
    }
    fun setImage(id:Int){
        this.image=image
    }

    fun getIsCompleted():Boolean{
        return isCompleted
    }
    fun setIsCompleted(isCompleted:Boolean){
        this.isCompleted=isCompleted
    }
    fun setIsSelected(isSelected: Boolean){
        this.isSeleceted=isSelected
    }
    fun getIsSelected():Boolean{
        return isSeleceted
    }

}