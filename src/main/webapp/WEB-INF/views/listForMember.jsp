<!-- 회원가입용입니다. かいいんとうろくようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list</title>
</head>
<body>
<h2>회원 목록 조회</h2>
<table>
    <tr>
        <th>id</th>
        <th>email</th>
        <th>password</th>
        <th>name</th>
        <th>age</th>
        <th>mobile</th>
        <th>상세조회</th>
        <th>삭제</th>
    </tr>
    <c:forEach items="${memberList}" var="member">
        <tr>
            <td>${member.id}</td>
            <td>

                <a href="/member?id=${member.id}">${member.memberEmail}</a>
            </td>
            <td>****(${member.memberPassword})</td>
            <td>${member.memberName}</td>
            <td>${member.memberAge}</td>
            <td>${member.memberMobile}</td>
            <td>
                <a href="/member?id=${member.id}">상세조회</a>
            </td>
            <td>
                <button onclick="deleteForMember('${member.id}')">삭제</button>
            </td>
        </tr>
    </c:forEach>
</table>
<button onclick="gotoindex()">홈페이지</button>
</body>
<script>
    const deleteForMember = (id) => {
        console.log(id);
        location.href = "/member/deleteForMember?id="+id;
    }

    const gotoindex = () => {
        location.href = "/";
    }
</script>
</html>