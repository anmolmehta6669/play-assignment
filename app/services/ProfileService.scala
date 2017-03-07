package services

import com.google.inject.{Singleton, Inject}
import models.Profile
import play.api.cache.CacheApi

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 7/3/17.
  */

@Singleton
class ProfileService @Inject() (cache:CacheApi)extends ProfileServiceTrait{
  override val profiles:ListBuffer[Profile]=ListBuffer(Profile("anmol","mehta","anmol.mehta@knoldus.in"))
  cache.set("anmol.mehta@knoldus.in",Profile("anmol","mehta","anmol.mehta@knoldus.in"))
  override def getProfile(email:String): Option[Profile] ={
    if(cache.get[Profile](email).isDefined) {

     cache.get[Profile](email)
    }
    else None
  }

// override def editProfileDetails(fname:String,lname:String,emailId:String): Boolean ={
//    if(profiles.map(_.email).contains(emailId)) {
//      profiles.update(profiles.map(_.email).indexOf(emailId),Profile(fname,lname,emailId))
//      true
//    }
//    else false
//  }

//  override def editProfileEmail(email:String): Boolean ={
//    if(profiles.map(_.email).contains(email)) {
//      val profile=getProfile(email)
//      profiles.update(profiles.map(_.email).indexOf(email),Profile(profile.get.firstName,profile.get.lastName,email))
//      true
//    }
//    else false
//  }

  override def createNewProfile(fname:String,lname:String,email:String): Boolean ={
    if(cache.get[Profile](email).isEmpty) {
      cache.set(email,Profile(fname,lname,email))
      println(cache.get[Profile](email).get)
      true
    }
    else false
  }

}
