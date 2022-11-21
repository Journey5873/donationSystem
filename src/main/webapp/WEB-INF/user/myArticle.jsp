<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyArticle</title>
    <style>
        .container {
            width: 70%;
            margin: 0 auto;
        }

        table {
            width: 90%;
            margin: 0 auto;
            border: 1px solid goldenrod;
            text-align: center;
            border-collapse: collapse;
        }

        th {
            border: 1px solid goldenrod;
            background-color: gold;
        }

        td {
            border: 1px solid goldenrod;
        }

        td:nth-child(1) {
            width: 20%;
        }

        td>img {
            width: 70%;
            height: 140px;
        }

        td:nth-child(2) {
            width: 30%;
        }

        td:nth-child(3),
        td:nth-child(4),
        td:nth-child(5),
        td:nth-child(6) {
            width: 10%;
        }

        button {
            background-color: gray;
            color: white;
            border-radius: 3px;
            border: none;
            transition: all 0.3s ease;
            padding: 10px;
        }

        button:hover {
            background-color: lightgray;
            color: black;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2>���� ������ ��� ���</h2>

        <hr>

        <table>
            <tr>
                <th>����</th>
                <th>����</th>
                <th>�ۼ� ��¥</th>
                <th>���� ��ݾ�(���� : ��)</th>
                <th>������ ���� ����</th>
                <th>����</th>
            </tr>

            <tr>
                <td>
                    <img src="../img/animal.png" alt="">
                </td>
                <td>�������� �α� �����</td>
                <td>2022-10-01</td>
                <td>5000000</td>
                <td>����</td>
                <td>
                    <button>UPDATE</button>
                </td>
            </tr>

            <tr>
                <td>
                    <img src="../img/cat.jpg" alt="">
                </td>
                <td>������ ���� ����̸� ���� ������ �ʿ��մϴ�</td>
                <td>2022-10-29</td>
                <td>150000000</td>
                <td>������</td>
                <td>
                    <button>UPDATE</button>
                </td>
            </tr>

            <tr>
                <td>
                    <img src="../img/rain.jpg" alt="">
                </td>
                <td>�̹� ����� ���� ���� ���������ϴ�.</td>
                <td>2022-10-02</td>
                <td>1500000</td>
                <td>������</td>
                <td>
                    <button>UPDATE</button>
                </td>
            </tr>
        </table>
    </div>
</body>

</html>