package yats

import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class DeveloperSpec extends Specification {

  void 'it creates developers, finds them and compares them'() {
    given:
    def erin = new Developer(fullName:'Erin', email:'erin@work.com')

    when:
    erin.save(flush:true)
    def found = Developer.find("from Developer d where d.fullName = :q", [q:'Erin'])

    then:
    found == erin
  }

  void 'it deletes cleanly' () {
    given:
    def erin = new Developer(fullName:'Erin', email:'erin@work.com').save(flush:true)

    when:
    erin.delete(flush:true)

    then:
    erin.isAttached() == false
    Developer.findByEmail('erin@work.com') == null
  }
}
