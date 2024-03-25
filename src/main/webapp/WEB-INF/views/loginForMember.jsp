<!-- 회원가입용입니다. かいいんとうろくようです -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<h2>로그인</h2>
    <form action="/member/loginForMember" method="post">
        <input type="text" name="memberEmail" placeholder="이메일">
        <input type="text" name="memberPassword" placeholder="비밀번호">
        <input type="submit" value="로그인">
    </form>
</body>
</html>