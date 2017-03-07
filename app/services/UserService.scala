package services

import com.google.inject.{Singleton, Inject}
import models.User
import play.api.cache.CacheApi

import scala.collection.mutable.ListBuffer

@Singleton
class UserService @Inject()(cache:CacheApi)extends UserServiceTrait{
  val users:ListBuffer[User]=ListBuffer(User("anmol.mehta@knoldus.in","anmol"))
  cache.set("anmol.mehta@knoldus.in",User("anmol.mehta@knoldus.in","anmol"))
  def getPassword(email:String): Option[String] ={
    if(hasAccount(email)) {

      Some(cache.get[User](email).get.password)
    }
      else None
    }

  def hasAccount(email:String):Boolean={
    if(cache.get[User](email).isDefined) {
      println(cache.get[User](email).get)
      true
    }
    else false
  }

//  def editUserPassword(emailId:String,password:String,newpassword:String): Boolean ={
//    if(hasAccount(emailId) && getPassword(emailId).get==password) {
//      users.update(users.map(_.emailId).indexOf(emailId),User(emailId,newpassword))
//      true
//    }
//    else false
//  }

//  def editUserEmail(email:String,newemail:String,password:String): Boolean ={
//    if(hasAccount(email) && getPassword(newemail).get==password) {
//      profile.editProfileEmail(email)
//      users.update(users.map(_.emailId).indexOf(email),User(newemail,password))
//      true
//    }
//    else false
//  }


  def createNewUser(email:String,password:String): Boolean ={
    if(!hasAccount(email)) {
      cache.set(email,User(email,password))
      true
    }
    else false
  }

}
