package models

import scala.collection.mutable.ListBuffer



object UserService{
  val users:ListBuffer[User]=ListBuffer(User("anmol.mehta@knoldus.in","anmol"));

  def getPassword(email:String): Option[String] ={
    if(hasAccount(email)) {
      Some(users(users.map(_.emailId).indexOf(email)).password)
    }
      else None
    }

  def hasAccount(email:String):Boolean={
    if(users.map(_.emailId).contains(email)) true
    else false
  }

  def editUserPassword(emailId:String,password:String,newpassword:String): Boolean ={
    if(hasAccount(emailId) && getPassword(emailId).get==password) {
      users.update(users.map(_.emailId).indexOf(emailId),User(emailId,newpassword))
      true
    }
    else false
  }

  def editUserEmail(email:String,newemail:String,password:String): Boolean ={
    if(hasAccount(email) && getPassword(newemail).get==password) {
      ProfileService.editProfileEmail(email)
      users.update(users.map(_.emailId).indexOf(email),User(newemail,password))
      true
    }
    else false
  }


  def createNewUser(email:String,password:String): Boolean ={
    if(!hasAccount(email)) {
      users.append(User(email,password))
      true
    }
    else false
  }

}
