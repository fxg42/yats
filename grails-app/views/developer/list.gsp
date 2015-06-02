<html>
  <head>
    <meta name="layout" content="main"/>
  </head>
  <body>
    <h1><span class="glyphicon glyphicon-user" aria-hidden="true"></span> List of developers</h1>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Full Name</th>
          <th>Email</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <% developers.each { d -> %>
        <tr>
          <td>${d.id}</td>
          <td>${d.fullName}</td>
          <td><a href="mailto:${d.email}"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> ${d.email}</a></td>
          <td><g:link action="edit" id="${d.id}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> edit</g:link></td>
        </tr>
        <% } %>
      </tbody>
    </table>
    <g:link action="edit" class="btn btn-default" role="button">Create new</g:link>
    <g:link action="foo" id="${42}" params="${[foo:'bar']}" class="btn btn-default" role="button">Something</g:link>
  </body>
</html>
