<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ID_CARD</title>
    <style>
    	.container {
            width: 70%;
            margin: 0 auto;
        }

        h2 {
            color: indianred;
        }

        form>label>img {
            width: 30%;
            height: 300px;
        }

        input {
            margin: 10px;
            padding: 5px;
        }

        .btn {
            margin: 0 auto;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }

        .btn>input {
            background-color: gray;
            border: none;
            color: white;
            padding: 10px;
            border-radius: 3px;
            transition: all 0.3s ease;
        }

        .btn>input:hover {
            background-color: lightgray;
            color: black;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2>ID Certification</h2>

        <div>(뒷 번호를 가리고 촬영해주세요)</div>

        <hr>

        <form action="">
            <label for="card"><img src="../img/id.jpg" alt=""></label>
            <br>
            <input type="file" id="card">
            <br><br><br>

            <div class="btn">
                <input type="button" value="Certify">
            </div>
        </form>
    </div>
</body>

</html>