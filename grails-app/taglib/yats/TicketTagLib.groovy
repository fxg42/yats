package yats

class TicketTagLib {
  static namespace = 'ticket'

  def typeIcon = { attrs, body ->
    switch(attrs.ticket.type.key) {
      case 'BUG':
        out << '<span class="glyphicon glyphicon-flash" aria-hidden="true"></span>'; break
      case 'IMPROVEMENT':
        out << '<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>'; break
      case 'NEW_FEATURE':
        out << '<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>'; break
      default:
        out << '<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>'; break
    }
  }

  def statusIcon = { attrs, body ->
    out << "<span class='label label-primary'>${attrs.status.key}</span>"
  }
}
