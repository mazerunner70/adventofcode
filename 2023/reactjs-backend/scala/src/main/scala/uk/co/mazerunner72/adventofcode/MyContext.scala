package uk.co.mazerunner72.adventofcode


import uk.co.mazerunner72.adventofcode.models.{AuthenticationException, AuthorisationException, User}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

case class MyContext(dao: DAO, currentUser: Option[User] = None){
  def login(email: String, password: String): User = {
    val userOpt = Await.result(dao.authenticate(email, password), Duration.Inf)
    userOpt.getOrElse(
      throw AuthenticationException("email or password are incorrect!")
    )
  }

  def ensureAuthenticated() =
    if(currentUser.isEmpty)
      throw AuthorisationException("You do not have permission. Please sign in.")
}
