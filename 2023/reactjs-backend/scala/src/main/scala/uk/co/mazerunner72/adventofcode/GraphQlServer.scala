package uk.co.mazerunner72.adventofcode

import akka.http.scaladsl.server.Route
import sangria.parser.QueryParser
import spray.json.{JsObject, JsString, JsValue}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import akka.http.scaladsl.server._
import sangria.ast.Document
import sangria.execution._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import sangria.marshalling.sprayJson._


object GraphQlServer {

  //#
  private val dao = DBSchema.createDatabase

  // #
  def endpoint(requestJSON: JsValue)(implicit ec: ExecutionContext): Route = {

    //#
    val JsObject(fields) = requestJSON

    //#
    val JsString(query) = fields("query")

    //#
    QueryParser.parse(query) match {
      case Success(queryAst) =>
        //#
        val operation = fields.get("operationName") collect {
          case JsString(op) => op
        }

        //#
        val variables = fields.get("variables") match {
          case Some(obj: JsObject) => obj
          case _ => JsObject.empty
        }
        //#
        complete(executeGraphQLQuery(queryAst, operation, variables))
      case Failure(error) =>
        complete(BadRequest, JsObject("error" -> JsString(error.getMessage)))
    }

  }

  private def executeGraphQLQuery(query: Document, operation: Option[String], vars: JsObject)(implicit ec: ExecutionContext) = {
    //#
    Executor.execute(
        GraphQlSchema.SchemaDefinition, //#
        query, //#
        MyContext(dao), //#
        variables = vars, //#
        operationName = operation,
        deferredResolver = GraphQlSchema.Resolver,
        exceptionHandler = GraphQlSchema.ErrorHandler,
        middleware = AuthMiddleware :: Nil
      ).map(OK -> _) //#
      .recover {
        case error: QueryAnalysisError => BadRequest -> error.resolveError
        case error: ErrorWithResolver => InternalServerError -> error.resolveError
      }
  }


}