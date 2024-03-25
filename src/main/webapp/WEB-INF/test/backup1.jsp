<!-- 게시판용입니다. けいじばんようです -->
<%--기존 보존용--%>
<%--@elvariable id="board" type="mapper"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>detail.jsp</title>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <td>${board.id}</td>
    </tr>
    <tr>
        <th>writer</th>
        <td>${board.boardWriter}</td>
    </tr>
    <tr>
        <th>date</th>
        <td>${board.boardCreatedTime}</td>
    </tr>
    <tr>
        <th>hits</th>
        <td>${board.boardHits}</td>
    </tr>
    <tr>
        <th>title</th>
        <td>${board.boardTitle}</td>
    </tr>
    <tr>
        <th>contents</th>
        <td>${board.boardContents}</td>
    </tr>
</table>
<button onclick="updateFn()">수정</button>
<button onclick="deleteFn()">삭제</button></br>
<!-- 페이징 기능이 있는 목록으로 돌아가기 -->
<!-- ページングきのうがあるもくろくにもどる -->
<button onclick="listFn()">페이징 있는 목록</button>
<!-- 페이징 기능이 없는 목록으로 돌아가기 -->
<!-- ページングきのうがないもくろくにもどる -->
<button onclick="listnonpFn()">페이징 없는 목록</button></br>
<button onclick="listSFn()">페이징X검색O 목록</button>
<button onclick="SPFn()">페이징O검색O 목록</button>

<div>
    <input type="text" id="commentWriter" value='${loginEmail}'readonly>
    <input type="text" id="commentContents" placeholder="내용">
    <button id="comment-writer-btn" onclick="commentWrite()">댓글작성</button>
</div>

<div id="comment-list">
    <table>
        <tr>
            <!-- <th>댓글번호</th> -->
            <th>작성자</th>
            <th>내용</th>
            <th>작성시간</th>
        </tr>
        <c:forEach items="${commentList}" var="comment" varStatus="status">
            <!-- 반복 작업이 필요하기 때문에 forEach 구문을 사용 -->
            <!--　くりかえしさぎょうがひつようなため、forEachこうぶんをしようします -->
            <tr data-commentid="${comment.commentid}">
                <!-- <td>${comment.commentid}</td> -->
                <td>${comment.commentWriter}</td>
                <td>${comment.commentContents}</td>
                <td>${comment.commentCreatedTime}</td>
                <td><button class="comment-delete-btn">댓글 삭제</button></td>
                <!-- <td><button id="comment-delete-btn" onclick="commentdelete()">댓글삭제</button></td> -->
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<script>
    const listFn = () => {
        const page = '${page}';
        location.href="/board/paging?page=" + page;
    }
    const updateFn = () => {
        const id = '${board.id}';
        location.href="/board/update?id=" + id;
    }
    const deleteFn = () => {
        const id = '${board.id}';
        location.href="/board/delete?id=" + id;
    }
    const listnonpFn = () => {
        location.href="/board/"
    }
    const listSFn = () => {
        location.href="/board/listSearch"
    }
    const SPFn = () => {
        location.href="/board/PagingAfterSearch"
    }

    // for 함수로 인해 난잡했던 부분을 ChatGPT의 도움을 받아 간략하게 수정하였습니다.
    // forかんすうによってらんざつだったぶぶんをChatGPTのたすけをかりてかんたんにしゅうせいしました。
    const commentWrite = () => {
        const writer = document.getElementById("commentWriter").value;
        const contents = document.getElementById("commentContents").value;
        const board = '${board.id}';
        $.ajax({
            type: "post",
            url: "/comment/save",
            data: {
                commentWriter: writer,
                commentContents: contents,
                boardId: board
            },
            dataType: "json",
            success: function (commentList) {
                console.log("작성성공");
                console.log(commentList);
                updateCommentList(commentList);
                // 삭제 버튼에 대한 클릭 이벤트 핸들러 다시 등록
                // さくじょボタンへのクリックイベントハンドラのさいとうろく
                attachDeleteEventHandlers();
            },
            error: function (request, status, error) {
                console.log("code: " + request.status)
                console.log("message: " + request.responseText)
                console.log("error: " + error);
            }
        });
    }


    // 삭제 버튼에 대한 클릭 이벤트 핸들러 등록
    // さくじょボタンへのクリックイベントハンドラのとうろく
    function attachDeleteEventHandlers() {
        document.querySelectorAll('.comment-delete-btn').forEach(button => {
            button.addEventListener('click', handleDelete);
        });
    }

    // 삭제 버튼 클릭 이벤트 핸들러
    // さくじょボタンクリックイベントハンドラ
    function handleDelete() {
        const button = this;
        // forEach 함수에서 특정한 값을 가져오는 법을 ChatGPT의 도움을 받아 변형하였습니다.
        // forEachかんすうからとくていのあたいをしゅとくするほうほうをChatGPTのたすけをかりてへんけいしました。
        const commentId = button.parentElement.parentElement.getAttribute('data-commentid');
        $.ajax({
            type: "POST",
            url: "/comment/DeleteCheck",
            data: {
                commentId: commentId
            },
            dataType: "text",
            success: function (res) {
                if (res === "Matched") {
                    $.ajax({
                        type: "POST",
                        url: "/comment/Delete",
                        data: {
                            commentId: commentId,
                            boardId: '${board.id}'
                        },
                        dataType: "json",
                        success: function (commentList) {
                            alert("댓글 삭제 성공하였습니다.");
                            updateCommentList(commentList);
                            // 삭제 버튼에 대한 클릭 이벤트 핸들러 다시 등록
                            // さくじょボタンへのクリックイベントハンドラのさいとうろく
                            attachDeleteEventHandlers();
                        },
                        error: function (request, status, error) {
                            console.log("code: " + request.status)
                            console.log("message: " + request.responseText)
                            console.log("error: " + error);
                        }
                    });
                } else {
                    alert("댓글 작성자가 아닙니다.");
                }
            },
            error: function (request, status, error) {
                console.log("code: " + request.status)
                console.log("message: " + request.responseText)
                console.log("error: " + error);
            }
        });
    }

    // 페이지 로드 시 삭제 버튼에 이벤트 핸들러 등록
    // ページロードじにさくじょボタンにイベントハンドラをとうろく
    document.addEventListener('DOMContentLoaded', () => {
        attachDeleteEventHandlers();
    });

    const updateCommentList = (commentList) => {
        let output = "<table>";
        output += "<tr>";
        output += "<th>작성자</th>";
        output += "<th>내용</th>";
        output += "<th>작성시간</th>";

        commentList.forEach(comment => {
            output += "<tr>";
            output += "<td>" + comment.commentWriter + "</td>";
            output += "<td>" + comment.commentContents + "</td>";
            output += "<td>" + comment.commentCreatedTime + "</td>";
            output += '<td><button class="comment-delete-btn" data-commentid="' + comment.commentid + '">댓글 삭제</button></td>';
            output += "</tr>";
        });

        output += "</table>";

        document.getElementById('comment-list').innerHTML = output;
        document.getElementById('commentWriter').value = '${loginEmail}';
        document.getElementById('commentContents').value = '';
    };
</script>
</html>