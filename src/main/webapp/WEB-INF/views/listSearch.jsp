<!-- 게시판용입니다. けいじばんようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>list</title>
</head>
<body>
<h2>페이징 없는 검색 게시판</h2>
<div>
    <form id="search" action="/board/listSearch" method="get">
    <select name="searchType" id="searchType">
        <option value="title">제목</option>
        <option value="content">내용</option>
        <option value="title_content">제목+내용</option>
        <option value="writer">작성자</option>
    </select>
        <input type="text" name="keyword" placeholder="검색어를 입력해주세요."/>
        <button type="submit">검색</button>
    </form>
</div>
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