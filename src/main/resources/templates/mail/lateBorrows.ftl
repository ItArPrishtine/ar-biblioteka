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
<table style="width: 100%">
    <tr>
        <td style="background-color: #F7F7F7; text-align: center; padding: 45px 0">
            <img align="center" alt="" src="https://mcusercontent.com/ace130bfdd851a2b9a1b3cf72/images/3941ecdb-caba-ccd9-606d-3682b35f988e.png" width="327.12" style="max-width:700px; padding-bottom: 0; display: inline !important; vertical-align: bottom;" class="mcnImage">
        </td>
    </tr>
    <tr>
        <td style="padding: 0 60px">

            <p style="font-family:Helvetica; color: #757575">Lista e personave me libra te huazuar: </p>

            <table>
                <thead>
                <tr>
                    <th style="border: 1px solid #dddddd;text-align: left;padding: 8px; background-color: #eee">Emri & Mbiemri</th>
                    <th style="border: 1px solid #dddddd;text-align: left;padding: 8px; background-color: #eee">Libri i huazuar</th>
                    <th style="border: 1px solid #dddddd;text-align: left;padding: 8px; background-color: #eee">Data e Huazimit</th>
                    <th style="border: 1px solid #dddddd;text-align: left;padding: 8px; background-color: #eee">Data e Kthimit</th>
                    <th style="border: 1px solid #dddddd;text-align: left;padding: 8px; background-color: #eee">Ditet me vonese</th>
                </tr>
                </thead>
                <tbody>
                <#list borrows as borrow>
                    <tr>
                        <td style="border: 1px solid #dddddd;text-align: left;padding: 8px;">${borrow.applicationUser.firstName} ${borrow.applicationUser.lastName}</td>
                        <td style="border: 1px solid #dddddd;text-align: left;padding: 8px;">${borrow.book.name}</td>
                        <td style="border: 1px solid #dddddd;text-align: left;padding: 8px;">${borrow.borrowFrom}</td>
                        <td style="border: 1px solid #dddddd;text-align: left;padding: 8px;">${borrow.borrowUntil}</td>
                        <td style="border: 1px solid #dddddd;text-align: left;padding: 8px;">${borrow.daysLeft}</td>
                    </tr>
                </#list>
                </tbody>
            </table>

            <hr style="margin: 30px 0"/>
        </td>
    </tr>
    <tr>
        <td style="background-color: #444444; padding: 30px 0">
            <table style="width: 100%">
                <tr>
                    <td style="width: 50%; text-align: right; color: white"><img width="50px; height: 50px" src="https://arsekretarite.com/assets/images/icons/facebook.svg"/></td>
                    <td style="width: 50%; text-align: left; color: white"><img width="50px; height: 50px" src="https://arsekretarite.com/assets/images/icons/instagram.svg"/></td>
                </tr>
            </table>
            <hr style="margin: 30px">
            <p style="color: #fff; padding: 0 30px; font-family: Helvetica">
                Per çfaredo problemi apo sugjerimi, ju lutem kontaktoni ne email-in <strong>it.arprishtine@gmail.com</strong>
            </p>
        </td>
    </tr>
</table>
</body>
</html>
