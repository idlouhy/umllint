<#escape x as x?html>
  <#macro page title>

  <!DOCTYPE html>
  <html lang="en-us">
  <head>
    <meta charset="utf-8"/>

    <title>UMLLint - ${title}</title>

    <meta name="description" content="A Tool for Checking Correctness of Design Diagrams in UML"/>
    <meta name="keywords" content="uml, xmi, umllint, lint, qvt, patterns"/>
    <meta name="author" content="Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/css/vendor/reset.css"/>
    <link rel="stylesheet" href="/css/layout.css"/>

  </head>

  <body>

  <h1>UMLLint - ${title}</h1>

  <div id="header">
    <div id="menu">
      <a href="/index.html">Home</a>
      <a href="/get.html">GET</a>
      <a href="/app/pattern/list">Patterns</a>
      <a href="/app/api/xml/patterns.xml">Export</a>
      <a href="/references.html">References</a>
      <a href="/about.html">About</a>
    </div>
  </div>

  <div id="content">
    <#nested>
  </div>

  <div id="footer">
    <p>
      &copy; 2014 Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
    </p>

    <div id="df-copyright">
    </div>
  </div>


  </body>
  </html>

  </#macro>
</#escape>