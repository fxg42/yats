<html>
  <head>
    <meta name="layout" content="main"/>
  </head>
  <body>
    <h1>${developer ? 'Update' : 'Create new'} developer</h1>
    <g:hasErrors bean="${developer}">
      <div class="alert alert-danger" role="alert">
        <ul>
          <g:eachError bean="${developer}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
          </g:eachError>
        </ul>
      </div>
    </g:hasErrors>
    <g:form action="save">
      <input type="hidden" name="id" value="${developer?.id}"/>
      <div class="form-group">
        <label for="email">Email address</label>
        <input type="email" class="form-control" name="email" value="${developer?.email}" placeholder="Enter email">
      </div>
      <div class="form-group">
        <label for="fullName">Full Name</label>
        <input type="text" class="form-control" name="fullName" value="${developer?.fullName}" placeholder="Enter full name">
      </div>
      <button type="submit" class="btn btn-default">Save</button>
    </g:form>
  </body>
</html>

