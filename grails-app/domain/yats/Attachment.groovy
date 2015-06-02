package yats

class Attachment {
  static belongsTo = Ticket

  byte[] bytes

  static constraints = {
    bytes maxSize:(1024 * 1024 * 2) // limit file size to 2MB.
  }
}
