package services

import com.google.inject.Singleton
import models.User

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 7/3/17.
  */


trait UserServiceTrait {


  val users: ListBuffer[String]

  def login(email:String,password:String):Boolean

  def showAllUser:ListBuffer[User]

  def showUser(email: String): Option[User]

  def hasAccount(email: String): Boolean

  //    def editUserPassword(emailId:String,password:String,newpassword:String): Boolean

  //    def editUserEmail(email:String,newemail:String,password:String): Boolean

  def disableUser(email:String):Boolean

  def enableUser(email:String):Boolean

  def createNewUser(user: User): Boolean
}
