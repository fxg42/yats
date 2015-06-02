package yats

class TicketController {

  def ticketService

  def index() {
    def tickets = Ticket.list()
    withFormat {
      html {
        [tickets: tickets]
      }
      json {
        render(tickets as grails.converters.JSON)
      }
    }
  }

  def create() {
    [ types: TicketType.list(),
      user: User.findByEmail('ursula@work.com'),
    ]
  }

  def attachment() {
    def ticket = Ticket.findByUuid(params.uuid)
    if (! ticket) {
      render(status:404)
    } else {
      def attachment = ticket.attachment
      if (! attachment) {
        render(status:404)
      } else {
        response.contentType = ticket.attachmentContentType
        response.setHeader('Content-Disposition', "attachment; filename=${ticket.attachmentOriginalFilename}")
        response.outputStream << attachment.bytes
      }
    }
  }

  def save() {
    def toSave = new Ticket()
    try {
      bindData(toSave, params, [include:['type', 'priority', 'description', 'summary', 'commissioner']])
      if (params.attachment.originalFilename) {
        toSave.attachmentContentType = params.attachment.contentType
        toSave.attachmentOriginalFilename = params.attachment.originalFilename
        toSave.attachment = new Attachment()
        bindData(toSave.attachment, params.attachment, [include:['bytes']])
      }
      ticketService.save(toSave)
      flash.success = 'ticket.creation.was.successful'
      redirect(action:'index')
    } catch(grails.validation.ValidationException e) {
      // show user errors
      flash.failed = toSave
      redirect(action:'create')
    } catch(Exception e) {
      // say you're sorry
      flash.error = e.message //'something.is.wrong'
      redirect(action:'create')
    }
  }
}
