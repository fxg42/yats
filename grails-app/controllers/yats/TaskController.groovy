package yats

class TaskController {

  def create() {
    render(view:'create', model:[
      availableDevelopers: Developer.list()
    ])
  }

  def save() {
    def toSave = new Task()
    bindData(toSave, params, [include:['responsibles']])
    toSave.summary = 'sum'
    toSave.description = 'desc'
    toSave.save(flush:true, failOnError:true)
    redirect(action:'create')
  }

}
