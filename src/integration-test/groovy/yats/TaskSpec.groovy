package yats


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class TaskSpec extends Specification {

  void 'it adds developers' () {
    given:
    def ticket = new Ticket( commissioner: User.findByEmail('ursula@work.com'), type: TicketType.findByKey('BUG'), summary: 'a summary...', description: 'a description...', priority: 1,).save(flush:true, failOnError:true)
    def task = new Task(origin:ticket, summary:'a summary...', description:'a description...')
    def alice = Developer.findByEmail('alice@work.com')

    when:
    task.addToResponsibles(alice)
    def result = task.save(flush:true, failOnError:true)

    then:
    result != null
    Task.list().first().responsibles.size() == 1
    Task.list().first().responsibles.first() == alice
  }
}
