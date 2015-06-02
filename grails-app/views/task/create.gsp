<html>
  <head>
    <meta name="layout" content="main"/>
  </head>
  <body>
    <%= flash.msg %>
    <g:form action="save">
      <div class="form-group">
        <label for="email">Developers</label>
        <g:select
          name="responsibles"
          from="${availableDevelopers}"
          multiple="yes"
          optionKey="id" />
      </div>
      <button type="submit" class="btn btn-default">Save</button>
    </g:form>
  </body>
</html>


