package yats

class Task {
  static hasMany = [responsibles:Developer]

  Ticket origin

  Boolean notificationSent = false
  String summary
  String description

  Date dateCreated

  static constraints = {
  }
}
