import yats.*
import grails.converters.*

class BootStrap {

  def grailsUrlMappingsHolder

  def init = { servletContext ->

    JSON.registerObjectMarshaller(TicketStatusUpdate) { update -> [
      status: update.status.key,
      starting_from: update.dateCreated,
    ]}

    JSON.registerObjectMarshaller(Ticket) { ticket ->
      def json = [
        uuid: ticket.uuid,
        summary: ticket.summary,
        description: ticket.description,
        commissioner: ticket.commissioner.email,
        priority: ticket.priority,
        created_on: ticket.dateCreated,
        type: ticket.type.key,
        progress: ticket.progress,
      ]
      if (ticket.attachmentId) {
        def controller = 'ticket'
        def action = 'attachment'
        def params = [uuid:ticket.uuid]
        def encoding = 'utf-8'

        json.attachment = [
          content_type: ticket.attachmentContentType,
          filename: ticket.attachmentOriginalFilename,
          _link: grailsUrlMappingsHolder.getReverseMapping(controller, action, params).createRelativeURL(controller, action, params, encoding)
        ]
      }
      json
    }

    def options = [flush:true, failOnError:true]

    new TicketType(key:'BUG', description:'bug').save(options)
    new TicketType(key:'IMPROVEMENT', description:'improvement').save(options)
    new TicketType(key:'NEW_FEATURE', description:'new feature').save(options)

    new TicketStatus(key:'OPENED', description:'opened').save(options)
    new TicketStatus(key:'IN_PROGRESS', description:'in progress').save(options)
    new TicketStatus(key:'RESOLVED', description:'resolved').save(options)
    new TicketStatus(key:'REOPENED', description:'reopened').save(options)
    new TicketStatus(key:'CLOSED', description:'closed').save(options)

    new Developer(fullName:'Alice', email:'alice@work.com').save(options)
    new Developer(fullName:'Bob', email:'bob@work.com').save(options)
    new Developer(fullName:'Carol', email:'carol@work.com').save(options)
    new Developer(fullName:'Dave', email:'dave@work.com').save(options)

    new User(fullName:'Ursula', email:'ursula@work.com').save(options)
  }
  def destroy = {
  }
}
