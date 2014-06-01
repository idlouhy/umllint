<#escape x as x?html>
<#macro page>

<html>
<head>
    <meta charset="utf-8"/>
    <title></title>

    <meta name="description" lang="en" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content="Ivo Dlouhý"/>

    <meta name="viewport" content="width=device-width,initial-scale=1">

    <link rel="stylesheet" href="/app/css/vendor/reset.css"/>
    <link rel="stylesheet" href="/app/css/layout.css"/>

    <script src="/app/js/vendor/jquery-1.10.2.min.js"></script>


</head>
<body>

    <div id="content">
        <#nested>
    </div>

    <div id="footer">&copy; Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz</div>
</body>
</html>

</#macro>
</#escape>