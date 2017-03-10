package controllers

import com.google.inject.Inject
import models.User
import play.api.data.Form
import play.api.mvc.Controller
import play.api.mvc._
import play.api.data.Forms._
import services.{MD5, UserServiceTrait, UserService}

class SignUp @Inject() (userService:UserServiceTrait)extends Controller {
  val newUserForm=Form(
    mapping(
      "emailId"-> email,
      "password"->nonEmptyText,
      "confirmPassword"->nonEmptyText,
      "firstName"->nonEmptyText,
      "lastName"->nonEmptyText,
      "age"->number(18,60),
      "role"->boolean,
      "isEnabled"->boolean
    )(User.apply)(User.unapply).verifying("password dont match", fields=> fields match {
      case data=> data.password==data.confirmPassword
    })
  )

  def addAccount=Action{ implicit request=>
     newUserForm.bindFromRequest.fold(
       error=> {
         Redirect(routes.HomeController.index())
       },
       userData =>{
         val user = userData.copy(password = MD5.hash(userData.password), confirmPassword = MD5.hash(userData.confirmPassword))
         if(userService.createNewUser(user))
         Redirect(routes.ProfileController.profile).withSession(
           "emailId" -> userData.emailId)
         else
           Redirect(routes.HomeController.index()).flashing(
             "error" -> "Email already exist or Password doesn't match Please Try Again!!")

       }
     )
   }

}

