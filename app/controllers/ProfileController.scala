package controllers

import com.google.inject.Inject
import models.Profile
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import services.{ProfileServiceTrait, ProfileService}

/**
  * Created by knoldus on 6/3/17.
  */
class ProfileController @Inject() (profileService:ProfileServiceTrait) extends Controller{

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
        val flag=profileService.createNewProfile(userData.firstName,userData.lastName,userData.email)
        if(flag)
        Ok("Data entered")
        else
          Redirect(routes.HomeController.index())
      }
    )
  }

  def profile = Action { implicit request=>
    request.session.get("emailId").map {
      email =>
        val profile = profileService.getProfile(email)
        Ok(views.html.profile(profile.get.firstName, profile.get.lastName, profile.get.email))
    }.getOrElse(Unauthorized("oops"))
  }

}
