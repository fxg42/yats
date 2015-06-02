package yats

import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class TicketSpec extends Specification {

  void 'when inserting, it adds an item to the progress' () {
    given:
    def ticket = new Ticket(
      commissioner: User.findByEmail('ursula@work.com'),
      type: TicketType.findByKey('BUG'),
      summary: 'a summary...',
      description: 'a description...',
      priority: 1,
    )

    when:
    def saved = ticket.save(flush:true, failOnError:true)

    then:
    saved != null
    ticket.errors.allErrors.size() == 0

    ticket.progress.size() == 1
    ticket.progress.head().status == TicketStatus.findByKey('OPENED')
  }

  void 'it finds tickets for a specific user' () {
    given:
    def bug = TicketType.findByKey('BUG')

    def ursula = User.findByEmail('ursula@work.com')
    def ulric = new User(fullName:'Ulric', email:'ulric@work.com').save(flush:true, failOnError:true)

    new Ticket(commissioner:ursula, type:bug, summary:'...', description:'', priority:1).save(flush:true, failOnError:true)
    new Ticket(commissioner:ursula, type:bug, summary:'...', description:'', priority:2).save(flush:true, failOnError:true)
    new Ticket(commissioner:ursula, type:bug, summary:'...', description:'', priority:3).save(flush:true, failOnError:true)
    new Ticket(commissioner:ulric,  type:bug, summary:'...', description:'', priority:4).save(flush:true, failOnError:true)

    when:
    def allTickets = Ticket.list()
    def ursulaTickets = Ticket.findAll('from Ticket t where t.commissioner = :user', [user:ursula])
    def ulricTickets = Ticket.findAll('from Ticket t where t.commissioner = :user', [user:ulric])

    then:
    allTickets.size() == 4
    ursulaTickets.size() == 3
    ulricTickets.size() == 1
    ulricTickets.head().priority == 4
  }

  void 'it allows progress' () {
    given:
    def ticket = new Ticket(
      commissioner: User.findByEmail('ursula@work.com'),
      type: TicketType.findByKey('BUG'),
      summary: 'a summary...',
      description: 'a description...',
      priority: 1,
    ).save(flush:true, failOnError:true)

    when:
    ticket.addToProgress(status:TicketStatus.findByKey('IN_PROGRESS'))
    ticket.addToProgress(status:TicketStatus.findByKey('RESOLVED'))
    ticket.addToProgress(status:TicketStatus.findByKey('CLOSED'))
    ticket.save(flush:true, failOnError:true)

    then:
    def found = Ticket.list().head()
    found.progress.size() == 4
    found.progress.first().status.key == 'OPENED'
    found.progress.last().status.key == 'CLOSED'
  }

  void 'it allows developers to work on tasks' () {
    given:
    def alice = Developer.findByEmail('alice@work.com')

    def ticket = new Ticket(
      commissioner: User.findByEmail('ursula@work.com'),
      type: TicketType.findByKey('BUG'),
      summary: 'a summary...',
      description: 'a description...',
      priority: 1,
    ).save(flush:true, failOnError:true)

    def task = new Task(origin:ticket, summary:'a summary...', description:'a description...')

    when:
    task.addToResponsibles(alice)
    task.save(flush:true, failOnError:true)

    then:
    def foundAlice = Developer.findByEmail('alice@work.com')
    foundAlice.tasks.first().origin == ticket
  }

  void 'it sets the Attachment after save' () {
    given:
    def ticket = new Ticket(
      commissioner: User.findByEmail('ursula@work.com'),
      type: TicketType.findByKey('BUG'),
      summary: 'a summary...',
      description: 'a description...',
      priority: 1,
      attachment: new Attachment(contentType:'application/pdf', originalFilename:'file.pdf')
    )

    when:
    ticket.save(flush:true, failOnError:true)

    then:
    def foundTicket = Ticket.list().first()

    ticket.attachment.ticket != null
    foundTicket.attachment.ticket != null
    foundTicket.attachment.ticket == foundTicket
    foundTicket.attachment.ticket == ticket
  }

  void 'it sets the Attachment' () {
    when:
    def ticket = new Ticket(
      commissioner: User.findByEmail('ursula@work.com'),
      type: TicketType.findByKey('BUG'),
      summary: 'a summary...',
      description: 'a description...',
      priority: 1,
      attachment: new Attachment(contentType:'application/pdf', originalFilename:'file.pdf')
    ).save()

    then:
    ticket.attachment.ticket != null
  }
}
