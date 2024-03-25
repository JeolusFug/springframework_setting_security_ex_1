<!-- 게시판용입니다. けいじばんようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script>
    /* controller에서 alert를 사용하기 위함 controllerでalertをしようするための */
    var msg = "<c:out value='${msg}'/>";
    alert(msg);
    var url = "<c:out value='${url}'/>";
    location.href = url;
</script>
</html>