<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입 </title>
</head>
<body>
<div>
    <div>
        <form method="post" action="/members/join">
            <div>
                <label>ID(email 형식)</label>
                <input type="text" name="email">
            </div>
            <div>
                <label>암호</label>
                <input type="password" name="password">
            </div>
            <div>
                <label>이름</label>
                <input type="text" name="name">
            </div>
            <div>
                <label></label>
                <input type="submit" value="회원가입등록">
            </div>
        </form>
    </div>
</div>
</body>
</html>