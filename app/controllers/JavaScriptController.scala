package controllers

import play.api.mvc._
import javax.inject
import play.api.routing.JavaScriptReverseRouter

/**
  * Created by knoldus on 11/3/17.
  */
class JavaScriptController extends Controller{
  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.ProfileController.manageUsers)
    )
  }
}
