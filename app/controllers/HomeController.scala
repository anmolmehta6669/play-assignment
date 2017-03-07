package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import services.{ProfileServiceTrait, ProfileService}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (profileService:ProfileServiceTrait)extends Controller {

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

}
