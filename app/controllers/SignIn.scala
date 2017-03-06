package controllers

import play.api.data.Form
import play.api.data.Forms._
import models.{UserService, User}
import play.api.mvc._

/**
  * Created by knoldus on 6/3/17.
  */
class SignIn extends Controller{
  val SignInForm= Form{

    mapping(
      "emailId" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)

  }

  def checkUser=Action { implicit request =>
    SignInForm.bindFromRequest.fold(
      formWithErrors => {
        Redirect(routes.HomeController.signIn()).flashing(
          "error" -> "Something went Wrong Please Try Again")
      },
      userData => {
        val flag: Boolean = UserService.hasAccount(userData.emailId)
        if (flag) Redirect(routes.HomeController.profile(userData.emailId)).withSession(
          "emailId" -> userData.emailId)
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
