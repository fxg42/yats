package yats

class TicketService {

  @grails.transaction.Transactional(readOnly=true)
  def findAllPending() {
  }

  @grails.transaction.Transactional
  def save(ticket) {
    ticket.save(flush:true, failOnError:true)
  }
}
