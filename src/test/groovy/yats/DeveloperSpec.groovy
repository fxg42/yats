package yats

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Developer)
class DeveloperSpec extends Specification {

  void 'it creates developers, finds them and compares them'() {
    given:
    def dave = new Developer(fullName:'Dave', email:'dave@dave.io')

    when:
    dave.save(flush:true, failOnError:true)

    then:
    Developer.findByFullName('Dave') == dave
  }
}
