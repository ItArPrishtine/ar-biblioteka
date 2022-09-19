<!doctype html>
<html>
<head>
    <title>
        Huazim i Ri
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
            <p style="width: 100%; display: inline-block">
                ${job?index + 1}. ${job.title} - ${job.link}
            </p>
        </#list>
    </#if>
</body>
</html>
