<!-- 게시판용입니다. けいじばんようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>list</title>
</head>
<body>
<h2>페이징 없는 게시판</h2>
<table>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>writer</th>
        <th>date</th>
        <th>hits</th>
    </tr>
    <c:forEach items="${boardList}" var="board">
        <tr>
            <td>${board.id}</td>
            <td>
                <a href="/board?id=${board.id}">${board.boardTitle}</a>
            </td>
            <td>${board.boardWriter}</td>
            <td>${board.boardCreatedTime}</td>
            <td>${board.boardHits}</td>
        </tr>
    </c:forEach>
</table>
<button onclick="writtingFn()">글쓰기</button>
<button onclick="gopagingFn()">페이징 목록</button>
<button onclick="gotoindex()">홈페이지</button>
<!-- <button onclick="gotologinpage2()">로그인페이지</button> -->
</body>
<script>
    const writtingFn = () => {
        const id = '${board.id}';
        location.href="/board/save?id=" + id;
    }
    const gopagingFn = () => {
        location.href="/board/paging/"
    }
    const gotoindex = () => {
        location.href="/"
    }
    const gotologinpage2 = () => {
        location.href="/member/loginForMember"
    }
</script>
</html>