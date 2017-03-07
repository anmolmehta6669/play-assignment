package controllers

import javax.inject._
import models.ProfileService
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def signUp = Action {
    Ok(views.html.signup("Sign Up"))
  }

  def signIn = Action { implicit request=>
    Ok(views.html.signin("Sign In"))
  }
  def profile = Action { implicit request=>
    request.session.get("emailId").map {
      email =>
        val profile = ProfileService.getProfile(email)
        Ok(views.html.profile(profile.get.firstName, profile.get.lastName, profile.get.email))
    }.getOrElse(Unauthorized("oops"))
  }

}
