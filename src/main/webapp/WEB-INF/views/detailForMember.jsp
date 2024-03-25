<!-- 회원가입용입니다. かいいんとうろくようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>detail</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <td>${member.id}</td>
    </tr>
    <tr>
        <th>email</th>
        <td>${member.memberEmail}</td>
    </tr>
    <tr>
        <th>password</th>
        <td>${member.memberPassword}</td>
    </tr>
    <tr>
        <th>name</th>
        <td>${member.memberName}</td>
    </tr>
    <tr>
        <th>age</th>
        <td>${member.memberAge}</td>
    </tr>
    <tr>
        <th>mobile</th>
        <td>${member.memberMobile}</td>
    </tr>
</table>
<!-- <button onclick="toupdate()">수정하기</button> -->
<button onclick="backpage()">뒤로가기</button>
</body>
<script>
    const toupdate = () => {
        location.href="/member/updateForMember"
    }
    const backpage = () => {
        location.href="/member/"
    }
</script>
</html>