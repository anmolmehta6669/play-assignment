package controllers

import com.google.inject.Inject
import models.NewUser
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
      "confirmPassword"->nonEmptyText
    )(NewUser.apply)(NewUser.unapply).verifying("password dont match", fields=> fields match {
      case data=> data.password==data.confirmPassword
    })
  )

  def addAccount=Action{ implicit request=>
     newUserForm.bindFromRequest.fold(
       error=> {
         Redirect(routes.HomeController.index())
       },
       userData =>{
         val user = userData.copy(password = MD5.hash(userData.password) )
         if(userService.createNewUser(user.emailId,user.password))
         Ok(views.html.register(userData.emailId))
         else
           Redirect(routes.HomeController.index())
       }
     )
   }

}

