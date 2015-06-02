package yats

class TicketStatusUpdate implements Comparable {
  static belongsTo = Ticket

  TicketStatus status

  // Normally, we wouldn't need to initialize this field because of its
  // standard name. We do, however, to make `compareTo` work while the entity is
  // transient.
  Date dateCreated = new Date()

  @Override int compareTo(other) {
    this.dateCreated <=> other.dateCreated
  }

  static constraints = {
  }
  static mappings = {
    dateCreated updateable:false
  }
}
