<html>
  <head>
    <meta name="layout" content="main"/>
  </head>
  <body>
    <h1>Create new ticket</h1>
    <g:hasErrors bean="${flash.failed}">
      <div class="alert alert-danger">
        <g:renderErrors bean="${flash.failed}" as="list" />
      </div>
    </g:hasErrors>
    <% if (flash.error) { %>
      <div class="alert alert-danger">
        <g:message code="${flash.error}"/>
      </div>
    <% } %>
    <g:uploadForm action="save">
      <div class="form-group">
        <label for="summary">Summary</label>
        <input type="text" class="form-control" name="summary" value="" placeholder="Enter a summary">
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <textarea name="description" class="form-control"></textarea>
      </div>
      <div class="form-group">
        <label for="type">Type</label>
        <g:select class="form-control" name="type" from="${types}" optionKey="id" optionValue="description"/>
      </div>
      <div class="form-group">
        <label for="type">Priority</label>
        <label class="radio-inline">
          <input type="radio" name="priority" value="1"> 1
        </label>
        <label class="radio-inline">
          <input type="radio" name="priority" value="2"> 2
        </label>
        <label class="radio-inline">
          <input type="radio" name="priority" value="3"> 3
        </label>
        <label class="radio-inline">
          <input type="radio" name="priority" value="4"> 4
        </label>
        <label class="radio-inline">
          <input type="radio" name="priority" value="5"> 5
        </label>
      </div>
      <div class="form-group">
        <label for="attachment">Attachment</label>
        <input type="file" class="form-control" name="attachment"/>
      </div>
      <input type="hidden" name="commissioner" value="${user.id}"/>
      <button type="submit" class="btn btn-default">Create</button>
    </g:uploadForm>
  </body>
</html>


