package services

import com.google.inject.Singleton
import models.Profile

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 7/3/17.
  */

trait ProfileServiceTrait {

  def profiles:ListBuffer[Profile]

  def getProfile(email:String): Option[Profile]

//  def editProfileDetails(fname:String,lname:String,emailId:String): Boolean

//  def editProfileEmail(email:String): Boolean

  def createNewProfile(fname:String,lname:String,email:String): Boolean

}
