package uk.co.mazerunner72.adventofcode

import sangria.execution.{Middleware, MiddlewareBeforeField, MiddlewareQueryContext}
import sangria.schema.{Action, Context}
import uk.co.mazerunner72.adventofcode.models.Authorised

object AuthMiddleware extends Middleware[MyContext] with MiddlewareBeforeField[MyContext] {
  override type QueryVal = Unit
  override type FieldVal = Unit

  override def beforeQuery(context: MiddlewareQueryContext[MyContext, _, _]) = ()

  override def afterQuery(queryVal: QueryVal, context: MiddlewareQueryContext[MyContext, _, _]) = ()

  override def beforeField(queryVal: QueryVal, mctx: MiddlewareQueryContext[MyContext, _, _], ctx: Context[MyContext, _]) = {
    val requireAuth = ctx.field.tags contains Authorised

    if(requireAuth) ctx.ctx.ensureAuthenticated()

    continue
  }
}
