package yats

@groovy.transform.EqualsAndHashCode(includes='email')
class Developer {
  static hasMany = [ tasks:Task ]
  static belongsTo = Task
  String fullName
  String email

  static constraints = {
    fullName blank:false, nullable:false
    email unique:true, blank:false, nullable:false
  }
  static mapping = {
    email updateable:false
  }
}
