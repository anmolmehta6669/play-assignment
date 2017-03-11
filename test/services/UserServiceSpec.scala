package services

/**
  * Created by knoldus on 9/3/17.
  */

import models.User
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.cache.CacheApi

import scala.Option

class UserServiceSpec extends PlaySpec with MockitoSugar{
    "User Service Should" should{
      "Email has account or Not" in{
        val cache=mock[CacheApi]
        val userService=new UserService(cache)
        when(cache.get[User]("a.b@c.com")).thenReturn(
          Some(User("a.b@c.com","password","password","fname","lname",20,true,true)))

        userService.hasAccount("a.b@c.com") mustBe true
      }

      "add account to cache with correct input" in{
        val cache=mock[CacheApi]
        val userService=new UserService(cache)
        when(cache.get[User]("haha@haha.com")).thenReturn(None)
        userService.createNewUser(User("haha@haha.com","","","","",20,true,true)) mustBe true
      }

      "add account to cache with incorrect input" in{
        val cache=mock[CacheApi]
        val userService=new UserService(cache)
        when(cache.get[User]("haha@haha.com")).thenReturn(Some(User("a.b@c.com","password","password","fname","lname",20,true,true)))
        userService.createNewUser(User("haha@haha.com","","","","",20,true,true)) mustBe false
      }

      "show user with correct input" in{
        val cache=mock[CacheApi]
        val userService=new UserService(cache)
        when(cache.get[User]("haha@haha.com")).thenReturn(
          Some(User("haha@haha.com","password","password","fname","lname",20,true,true)))
        userService.showUser("haha@haha.com") mustBe Some(User(
          "haha@haha.com","password","password","fname","lname",20,true,true))

      }

      "show user with incorrect input" in{
        val cache=mock[CacheApi]
        val userService=new UserService(cache)
        when(cache.get[User]("haha@haha.com")).thenReturn(None)
        userService.showUser("haha@haha.com") mustBe None

      }

      "disable user with correct input" in{

      }

    }
}
