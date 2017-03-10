package models

/**
  * Created by knoldus on 5/3/17.
  */
case class User(emailId:String,password:String,confirmPassword:String,firstName:String, lastName:String, age:Int, role:Boolean, isEnabled:Boolean)
