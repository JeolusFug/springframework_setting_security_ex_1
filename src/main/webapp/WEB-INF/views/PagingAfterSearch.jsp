<!-- 게시판용입니다. けいじばんようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>PagingAfterSearch</title>
</head>
<body>
<h2>페이징 + 검색 게시판</h2>
<div>
    <form id="search" action="/board/PagingAfterSearch" method="get">
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
                    <a href="/board?id=${board.id}&page=${pagingSearch.page}">${board.boardTitle}</a>
                </td>
                <td>${board.boardWriter}</td>
                <td>${board.boardCreatedTime}</td>
                <td>${board.boardHits}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<c:choose>
    <c:when test="${pagingSearch.page<=1}">
        <span>[이전]</span>
    </c:when>
    <c:when test="${keyword!=null}">
        <c:choose>
            <c:when test="${pagingSearch.page<=1}">
                <span>[이전]</span>
            </c:when>
            <c:otherwise>
                <a href="/board/PagingAfterSearch?page=${pagingSearch.page-1}&searchType=${searchType}&keyword=${keyword}">[이전]</a>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <a href="/board/PagingAfterSearch?page=${pagingSearch.page-1}">[이전]</a>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${keyword==null}">
        <c:forEach begin="${pagingSearch.startPage}" end="${pagingSearch.endPage}" var="i" step="1">
            <c:choose>
                <c:when test="${i eq pagingSearch.page}">
                    <span>${i}</span>
                </c:when>

                <c:otherwise>
                    <a href="/board/PagingAfterSearch?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:when>

    <c:otherwise>
        <c:forEach begin="${pagingSearch.startPage}" end="${pagingSearch.endPage}" var="i" step="1">
            <c:choose>
                <c:when test="${i eq pagingSearch.page}">
                    <span>${i}</span>
                </c:when>

                <c:otherwise>
                    <a href="/board/PagingAfterSearch?page=${i}&searchType=${searchType}&keyword=${keyword}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:otherwise>
</c:choose>


<c:choose>
    <c:when test="${pagingSearch.page>=pagingSearch.maxPage}">
        <span>[다음]</span>
    </c:when>
    <c:when test="${keyword!=null}">
        <c:choose>
            <c:when test="${pagingSearch.page>=pagingSearch.maxPage}">
                <span>[다음]</span>
            </c:when>
            <c:otherwise>
                <a href="/board/PagingAfterSearch?page=${pagingSearch.page+1}&searchType=${searchType}&keyword=${keyword}">[다음]</a>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <a href="/board/PagingAfterSearch?page=${pagingSearch.page+1}">[다음]</a>
    </c:otherwise>
</c:choose>


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