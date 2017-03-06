package models

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 7/3/17.
  */
object ProfileService {
  val profiles:ListBuffer[Profile]=ListBuffer(Profile("anmol","mehta","anmol.mehta@knoldus.in"));

  def getProfile(email:String): Option[Profile] ={
    if(profiles.map(_.email).contains(email)) {
      Some(profiles(profiles.map(_.email).indexOf(email)))
    }
      else None
  }

  def editProfileDetails(fname:String,lname:String,emailId:String): Boolean ={
    if(profiles.map(_.email).contains(emailId)) {
      profiles.update(profiles.map(_.email).indexOf(emailId),Profile(fname,lname,emailId))
      true
    }
    else false
  }

  def editProfileEmail(email:String): Boolean ={
    if(profiles.map(_.email).contains(email)) {
      val profile=getProfile(email)
      profiles.update(profiles.map(_.email).indexOf(email),Profile(profile.get.firstName,profile.get.lastName,email))
      true
    }
    else false
  }

  def createNewProfile(fname:String,lname:String,email:String): Boolean ={
    if(UserService.users.map(_.emailId).contains(email) && !profiles.map(_.email).contains(email)) {
      profiles.append(Profile(fname,lname,email))
      true
    }
    else false
  }

}
