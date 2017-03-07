package controllers

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import models.User
import play.api.mvc._
import services.{UserServiceTrait, UserService}

/**
  * Created by knoldus on 6/3/17.
  */
class SignIn @Inject() (userService:UserServiceTrait)extends Controller{
  val SignInForm= Form{

    mapping(
      "emailId" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)

  }

  def checkUser=Action { implicit request =>
    SignInForm.bindFromRequest.fold(
      formWithErrors => {
//        println("tmkc")
        Redirect(routes.HomeController.signIn()).flashing(
          "error" -> "Something went Wrong Please Try Again")
      },
      userData => {
//        println("ok h bhau")
        val flag: Boolean = userService.hasAccount(userData.emailId)
        if (flag) {
          Redirect(routes.ProfileController.profile).withSession(
            "emailId" -> userData.emailId)
        }
        else {
          /*Redirect(routes.HomeController.firstPage())*/
          Redirect(routes.HomeController.signIn()).flashing(
            "error" -> "UserName Or Password is wrong Please Try Again!!")

        }
      }
    )
  }

  def changePassword=Action{
    Ok("Here Password will be changed and session will be maintained")
  }
  def changeDetails=Action{
    Ok("here name will be changed and session will be maintained.")
  }

}
