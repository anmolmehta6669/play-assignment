package controllers

import models.Profile
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import models.ProfileService
/**
  * Created by knoldus on 6/3/17.
  */
class ProfileController extends Controller{

  val userDetails=Form(
    mapping(
      "firstName"-> nonEmptyText,
      "lastName"->nonEmptyText,
      "email"->email
    )(Profile.apply)(Profile.unapply)
  )
  def addProfile=Action{ implicit request=>
    userDetails.bindFromRequest.fold(
      error=> {
        Redirect(routes.HomeController.index())
      },
      userData =>{
        if(ProfileService.createNewProfile(userData.firstName,userData.lastName,userData.email))
        Ok("Data entered")
        else
          Redirect(routes.HomeController.index())
      }
    )
  }


}
