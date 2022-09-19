<!doctype html>
<html>
<head>
    <title>
        Pozita pune per ju :)
    </title>
    <style>
        * {
            font-family: 'Helvetica';
        }
    </style>

</head>
<body style="margin: 0; padding: 0">
    <#if jobs??>
        <#list jobs as job>
            <a style="width: 100%; display: block" href="${job.link}">${job?index + 1}. ${job.title}</a>
        </#list>
    </#if>
</body>
</html>
