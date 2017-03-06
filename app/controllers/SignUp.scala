package controllers

import models.NewUser
import play.api.data.Form
import play.api.mvc.Controller
import play.api.mvc._
import play.api.data.Forms._
import models.UserService

class SignUp extends Controller {
  val newUserForm=Form(
    mapping(
      "emailId"-> email,
      "password"->nonEmptyText,
      "confirmPassword"->nonEmptyText
    )(NewUser.apply)(NewUser.unapply)
  )

  def addAccount=Action{ implicit request=>
     newUserForm.bindFromRequest.fold(
       error=> {
         Redirect(routes.HomeController.index())
       },
       userData =>{
         if(UserService.createNewUser(userData.emailId,userData.password))
         Ok(views.html.register(userData.emailId))
         else
           Redirect(routes.HomeController.index())
       }
     )
   }

}

