<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed</title>
    <style>
        .container {
            width: 70%;
            margin: 0 auto;
        }

        .container .board {
            padding: 10px;
            display: flex;
            justify-content: space-between;
        }

        .post {
            font-size: x-large;
            font-weight: bold;
            color: indianred;
        }

        select {
            width: 25%;
        }

        table {
            width: 100%;
            text-align: center;
            font-size: large;
        }

        td {
            width: 25%;
            padding: 10px;
        }

        td:hover {
            cursor: pointer;
            background-color: lightgray;
        }

        td img {
            width: 100%;
            height: 200px;
        }

        .bottom {
            display: flex;
            flex-direction: row;
            justify-content: center;
        }

        .bottom>input {
            margin: 10px;
        }
    </style>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <div class="board">
            <div class="post">VIEWS</div>

            <select name="" id="">
                <option value="">悼拱</option>
                <option value="">犁抄 犁秦</option>
                <option value="">荤雀 秒距 拌摸</option>
            </select>
        </div>

        <hr>

        <table>
            <tr>
                <td>
                    <div>力格</div>
                    <img src="../img/cat.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/disaster.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/animal.png" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/rain.jpg" alt="">
                </td>
            </tr>

            <tr>
                <td>
                    <div>力格</div>
                    <img src="../img/animal.png" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/cat.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/volunteer.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/rain.jpg" alt="">
                </td>
            </tr>

            <tr>
                <td>
                    <div>力格</div>
                    <img src="../img/rain.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/animal.png" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/disaster.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/cat.jpg" alt="">
                </td>
            </tr>
        </table>
    </div>
</body>

</html>