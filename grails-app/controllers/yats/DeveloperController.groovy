package yats

class DeveloperController {

  def ticketService
  
  def list() {
    render(view:'list', model:[
      developers: Developer.list()
    ])
  }

  def edit() {
    render(view:'form', model:[
      developer: params.id ? Developer.get(params.id) : null
    ])
  }

  @grails.transaction.Transactional
  def save() {
    def target = params.id ? Developer.get(params.id) : new Developer()
    bindData(target, params, [include:['fullName', 'email']])
    if (target.save(flush:true)) {
      flash.message = 'you saved!'
      redirect(action:'list')
    } else {
      render(view:'form', model:[developer:target])
    }
  }
}
