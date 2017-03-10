package services

import com.google.inject.{Singleton, Inject}
import models.User
import play.api.cache.CacheApi

import scala.collection.mutable.ListBuffer

@Singleton
class UserService @Inject()(cache: CacheApi) extends UserServiceTrait {
  val users: ListBuffer[String] =ListBuffer("anmol.mehta@knoldus.in")
  cache.set("anmol.mehta@knoldus.in",
    User("anmol.mehta@knoldus.in", MD5.hash("password"),MD5.hash("password"), "Anmol", "Mehta", 23,true,true))

  def hasAccount(email: String): Boolean = cache.get[User](email).isDefined

  def createNewUser(user: User): Boolean = {
    if (!hasAccount(user.emailId)) {
      users.append(user.emailId)
      cache.set(user.emailId, user)
      true
    }
    else false
  }
  def login(email: String,password:String):Boolean = {
    if (hasAccount(email) && cache.get[User](email).get.password==password &&
      cache.get[User](email).get.isEnabled)
    true
    else false
  }

  def disableUser(email:String): Boolean ={
    if(hasAccount(email)) {
      val temp = cache.get[User](email).get
      val user = temp.copy(isEnabled = false)
      cache.set(user.emailId, user)
      true
    }
    else false
  }
  def enableUser(email:String): Boolean ={
    if(hasAccount(email)) {
      val temp = cache.get[User](email).get
      val user = temp.copy(isEnabled = true)
      cache.set(user.emailId, user)
      true
    }
    else false
  }

  def showUser(email: String): Option[User] = {
    if (hasAccount(email))
    cache.get[User](email)
    else None
  }

  def showAllUser(): ListBuffer[User] ={
    println(users.map(email=> showUser(email).get))
    users.map(email=> showUser(email).get)
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



}
