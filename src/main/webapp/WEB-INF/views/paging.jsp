<!-- 게시판용입니다. けいじばんようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>paging</title>
</head>
<body>
<h2>페이징 있는 게시판</h2>
<div>
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
                    <a href="/board?id=${board.id}&page=${paging.page}">${board.boardTitle}</a>
                </td>
                <td>${board.boardWriter}</td>
                <td>${board.boardCreatedTime}</td>
                <td>${board.boardHits}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
    <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌
             げんざいのペエジが１ペエジならもじだけひょうじ --%>
        <c:when test="${paging.page<=1}">
            <span>[이전]</span>
        </c:when>
        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청
         　　１ペエジでないばあいは、[이전]をクリックするとげんざいのペエジよりひとつすくないペエジリクエスト --%>
        <c:otherwise>
            <a href="/board/paging?page=${paging.page-1}">[이전]</a>
        </c:otherwise>
    </c:choose>

    <%--  for(int i=startPage; i<=endPage; i++)      --%>
    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
        <c:choose>
            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게
             　　　ようせいしたペエジにあるばあい、げんざいのペエジばんごうはテキストのみみえるように --%>
            <c:when test="${i eq paging.page}">
                <span>${i}</span>
            </c:when>

            <c:otherwise>
                <a href="/board/paging?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span>[다음]</span>
        </c:when>
        <c:otherwise>
            <a href="/board/paging?page=${paging.page+1}">[다음]</a>
        </c:otherwise>
    </c:choose>
    <button onclick="rewriteFn()">글쓰기</button>
    <button onclick="nonpaging()">페이징 없는 목록</button>
    <button onclick="goindex()">홈페이지</button>
    <!-- <button onclick="gotologinpage1()">로그인페이지</button> -->
</div>
</body>
<script>
    const rewriteFn = () => {
        const id = '${board.id}';
        location.href="/board/save?id=" + id;
    }
    const nonpaging = () => {
        location.href="/board/"
    }
    const goindex = () => {
        location.href="/"
    }
    const gotologinpage1 = () => {
        location.href="/member/loginForMember"
    }
</script>
</html>