package services

import com.google.inject.Singleton
import models.User

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 7/3/17.
  */


trait UserServiceTrait {


    val users:ListBuffer[User]

    def getPassword(email:String): Option[String]

    def hasAccount(email:String):Boolean
//    def editUserPassword(emailId:String,password:String,newpassword:String): Boolean

//    def editUserEmail(email:String,newemail:String,password:String): Boolean


    def createNewUser(email:String,password:String): Boolean
}
