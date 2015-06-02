package yats

@groovy.transform.EqualsAndHashCode(includes='key')
class TicketStatus {
  String key
  String description

  static constraints = {
    key unique:true, blank:false, nullable:false
    description blank:false, nullable:false
  }
  static mapping = {
    key updateable:false
  }
}
