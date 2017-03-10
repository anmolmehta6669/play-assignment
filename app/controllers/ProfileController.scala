package controllers

import com.google.inject.Inject
import models.User

import play.api.mvc._
import services.UserServiceTrait

/**
  * Created by knoldus on 6/3/17.
  */
class ProfileController @Inject()(userService: UserServiceTrait) extends Controller {


  def profile = Action { implicit request =>
    request.session.get("emailId").map {
      email =>
        val profile = userService.showUser(email)
        if (profile.isDefined)
          Ok(views.html.profile(
            User(profile.get.emailId, profile.get.password, profile.get.confirmPassword,
              profile.get.firstName, profile.get.lastName, profile.get.age, profile.get.role, profile.get.isEnabled)
          )).withNewSession
        else
          Ok("Some Error occured, Sorry for the inconvinience")
    }.getOrElse(Unauthorized("oops"))
  }

  def logout = Action {
    implicit request => {
      Redirect(routes.HomeController.index()).flashing(
        "error" -> "Logged Out Succesfully!!").withNewSession

    }
  }

  def changePassword = Action {
    Ok("here you will change password")
  }

  def changeDetails = Action {
    Ok("here you will change details")
  }

  def manageUsers = Action {
    implicit request => {
      val allUsers = userService.showAllUser
      println(allUsers)
      Ok(views.html.users(allUsers))
    }
  }

  def disable(email: String) = Action {
    implicit request => {
      if (userService.disableUser(email))
        Redirect(routes.ProfileController.manageUsers())
      else
        Redirect(routes.HomeController.index()).flashing (
          "error" -> " Please Try Again!!")
    }

  }

  def enable(email: String) = Action {
    implicit request => {
      if (userService.enableUser(email))
        Redirect(routes.ProfileController.manageUsers())
      else
        Redirect(routes.HomeController.index()).flashing (
          "error" -> " Please Try Again!!")
    }
  }

}
