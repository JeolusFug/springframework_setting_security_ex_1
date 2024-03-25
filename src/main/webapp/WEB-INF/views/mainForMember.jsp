<!-- 회원가입용입니다. かいいんとうろくようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>main</title>
</head>
<body>
    <h2>${sessionScope.loginEmail} 님 로그인 되었습니다.</h2>
    <button onclick="update()">내정보 수정하기</button>
    <button onclick="logout()">로그아웃</button>
    <h2>게시판 바로가기</h2>
    <button onclick="gotononpboard()">페이징 없는 게시판 바로가기</button>
    <button onclick="gotopboard()">페이징 있는 게시판 바로가기</button>
    <h2>검색 게시판 바로가기</h2>
    <button onclick="gotosearchboard()">페이징 없는 검색 게시판 바로가기</button>
    <button onclick="gotosearchpagingboard()">검색 + 페이징 게시판 바로가기</button>
</body>
<script>
    const update = () => {
        location.href = "/member/updateForMember";
    }
    const logout = () => {
        sessionStorage.clear();
        location.href = "/";
    }
    const gotononpboard = () => {
        location.href = "/board/";
    }
    const gotopboard = () => {
        location.href = "/board/paging";
    }
    const gotosearchboard = () => {
        location.href = "/board/listSearch";
    }
    const gotosearchpagingboard = () => {
        location.href = "/board/PagingAfterSearch";
    }
</script>
</html>