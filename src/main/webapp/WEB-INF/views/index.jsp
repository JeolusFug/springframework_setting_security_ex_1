<!-- 게시판용입니다. けいじばんようです -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>스프링 프레임워크 게시판</h2></br>
    <h3>spring framework board</h3>
    <a href="board/save1">글작성(페이징 없음)</a>
    <a href="board/">글목록(페이징 없음)</a></br>
    <a href="board/save">글작성(페이징 있음)</a>
    <a href="/board/paging">글목록(페이징 있음)</a>
    <a href="views2/indexforMember.jsp">멤버</a>
</body>
</html>
