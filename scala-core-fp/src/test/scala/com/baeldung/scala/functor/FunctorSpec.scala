package com.baeldung.scala.functor

import cats.Functor
import org.scalatest.matchers.should.Matchers
import org.scalatest.{FeatureSpec, GivenWhenThen}

import java.io.IOException
import scala.util.{Failure, Success, Try}

class FunctorSpec extends FeatureSpec with GivenWhenThen  with Matchers {
    class EnveloperService {
        def envelop(str: String): String = {
            s"Envelop($str)"
        }
    }

    def sendData(data: String)(implicit enveloper: EnveloperService) =
        enveloper.envelop(data)

    implicit val myEnveloper = new EnveloperService()
    myEnveloper.envelop("Some Data")


    implicit def function1Functor[In]: Functor[(In) => *] =
        new Functor[(In) => *] {
            def map[A, B](fa: In => A)(f: A => B): (In) => B = fa andThen f
        }

    val f1: String => Int = a => a.toInt

    function1Functor.map(f1) {res =>
        s"Hi! $res"
    }




  Feature("Legacy code invocation") {
    Scenario("Accidentally Using Null value as an object") {
      Given("Unknown user as a request gives a null as response")
//      val nullSession: Session = LegacyService.authenticate(null)

      When("We try to access an objects field")
      Then("NullPointerException should be thrown")
      intercept[NullPointerException] {
//        val nullSession = LegacyService.authenticate(null)
//        val id: String = nullSession.id
      }
    }

    Scenario("Legacy code could throw an exception") {
      Given("Outage on connectivity level")
      When("We try to invoke a legacy code")
      Then("IOException could be thrown")
      intercept[IOException] {
//        val exceptionResource = LegacyService.getResource("/", Session("sessionId", 3600))
      }
    }
  }

  Feature("Use Option[T] to guard against null") {
    Scenario("Box legacy code in Option[T] while calling") {
      Given("Optional result")
//      val optionalSession = Option(LegacyService.authenticate(null))

      When("We check a result")
//      val isDefined = optionalSession.isDefined

      Then("It's undefined")
//      isDefined should be(false)
//      optionalSession should be(None)
    }

    Scenario("Provide an adapted version of a legacy code with Option[T]") {
      Given("Call to an adapter")
//      val optionalSession = HandlingWithOption.getResourceOptional(null, null)

      When("We check a result")
//      val isDefined = optionalSession.isDefined

      Then("It's undefined")
//      isDefined should be(false)
//      optionalSession should be(None)
    }
  }
}
