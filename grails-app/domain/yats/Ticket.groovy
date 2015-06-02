package yats

@groovy.transform.EqualsAndHashCode(includes='uuid')
class Ticket {
  String uuid = UUID.randomUUID().toString()

  SortedSet progress
  static hasMany = [ progress:TicketStatusUpdate ]

  Attachment attachment
  User commissioner
  TicketType type

  Integer priority
  String description
  String summary

  String attachmentContentType
  String attachmentOriginalFilename

  Date dateCreated

  def beforeInsert() {
    addToProgress(status:TicketStatus.findByKey('OPENED'))
  }

  static constraints = {
    uuid unique:true, blank:false, nullable:false
    summary blank:false, nullable:false
    description blank:true, nullable:true
    priority range:1..5, nullable:false
    attachment unique:true, nullable:true
    attachmentContentType nullable:true
    attachmentOriginalFilename nullable:true
  }
  static mappings = {
    uuid updateable:false
    progress cascade:'all-delete-orphan'
  }
}
