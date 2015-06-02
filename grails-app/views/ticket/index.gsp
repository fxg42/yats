<html>
  <head>
    <meta name="layout" content="main"/>
  </head>
  <body>
    <h1><span class="glyphicon glyphicon-tags" aria-hidden="true"></span> List of tickets</h1>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Type</th>
          <th>Priority</th>
          <th>Summary</th>
          <th>Current status</th>
          <th>Available actions</th>
        </tr>
      </thead>
      <tbody>
        <% tickets.each { t -> %>
        <tr>
          <td><ticket:typeIcon ticket="${t}"/></td>
          <td>${t.priority}</td>
          <td>${t.summary}</td>
          <td><ticket:statusIcon status="${t.progress.last().status}"/></td>
          <td>
            <% if (t.attachmentId) { %>
            <g:link action="attachment" params="${[uuid:t.uuid]}"><span class="glyphicon glyphicon-file" aria-hidden="true"></span> ${t.attachmentOriginalFilename}</g:link>
            <% } %>
          </td>
        </tr>
        <% } %>
      </tbody>
    </table>
    <g:link action="create" class="btn btn-default" role="button">Create new</g:link>
  </body>
</html>
