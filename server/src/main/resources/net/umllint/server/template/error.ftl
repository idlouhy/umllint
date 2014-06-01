<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>

<p>List of patterns:</p>

<ul>
<#list patterns as pattern>
    <li>${pattern.id}</li>
</#list>
</ul>

</body>
</html>