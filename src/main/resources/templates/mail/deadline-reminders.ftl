<!doctype html>
<html>
<head>
    <title>
        Rikujtim për librin e huazuar
    </title>
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

            <p style="font-family:Helvetica; color: #757575">Pershendetje ${username},</p>
                <#if (days > 0)>
                    <p style="font-family:Helvetica; color: #757575">
                        Sekretaria e Bibliotekës iu rikujton se ju kanë mbetur edhe ${days} ditë për të lexuar librin e huazuar ${bookName}.
                        Gjithashtu, nëse kjo kohë nuk ka mjaftuar, ju mund të shtyni datën e kthimit për 1 javë duke klikuar tek butoni më poshtë..
                    </p>

                    <p style="font-family:Helvetica; color: #757575">Ju lutemi të respektoni datën e kthimit. </p>
            
                <#elseif days == 0>
                    <p style="font-family:Helvetica; color: #757575">
                        Sekretaria e Bibliotekës iu rikujton se sot eshte dita e fundit per ta kthyer librin ${bookName} tek biblioteka.
                    </p>

                <#else>
                    <p style="font-family:Helvetica; color: #757575">
                        Sekretaria e Bibliotekës iu rikujton se kanë kaluar ${days} ditë, që nga data e kthimit të librit ${bookName}.
                    </p>
            </#if>
            
            <p style="font-family:Helvetica; color: #757575">
                Gjithë të mirat,
                Biblioteka Platon.
            </p>

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
