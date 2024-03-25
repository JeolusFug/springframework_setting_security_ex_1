<!-- 게시판용입니다. けいじばんようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<button onclick="listFn()">페이징 있는 목록</button>
<button onclick="listnonpFn()">페이징 없는 목록</button></br>
<button onclick="listSFn()">페이징X검색O 목록</button>
<button onclick="SPFn()">페이징O검색O 목록</button>

<div>
    <input type="text" id="commentWriter" value='${loginEmail}' readonly>
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
            <tr data-commentid="${comment.commentid}">
                <!-- <td>${comment.commentid}</td> -->
                <td data-commentWriter="${comment.commentWriter}">${comment.commentWriter}</td>
                <td data-commentContents="${comment.commentContents}">${comment.commentContents}</td>
                <td>${comment.commentCreatedTime}</td>
                <td><button class="comment-delete-btn">댓글 삭제</button></td>
                <td><button class="pdldyd">댓글 수정</button></td>
                    <%--                <p id="update"></p>--%>
                <!--                <td>${commentList[status.count].commentContents}</td>-->
                    <%--                <td><button class="comment-update-btn">댓글 수정</button></td>--%>
                    <%--                <td><button class="btnbtn">버튼2</button></td>--%>
                    <%--                <td><button class="btnbtn2">버튼3</button></td>--%>
            </tr>
        </c:forEach>
    </table>
</div>

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

    //수정창 생성
    const updateForm = () => {
        document.querySelectorAll('.pdldyd').forEach(button => {
            button.addEventListener('click', () => {
                // 이전에 열려있던 모든 수정 창을 닫음
                document.querySelectorAll('.updatejung').forEach(form => {
                    form.remove();
                });

                const commentId = button.parentElement.parentElement.getAttribute('data-commentid');
                const commentWriter = button.parentElement.parentElement.querySelector('td[data-commentWriter]').textContent;
                const commentContents = button.parentElement.parentElement.querySelector('td[data-commentContents]').textContent;
                // const commentWriter = button.parentElement.parentElement.querySelector('td:first-child').textContent;
                // const commentContents = button.parentElement.parentElement.querySelector('td:nth-child(2)').textContent;
                console.log("commentId = " + commentId + ", commentWriter = " + commentWriter + ", commentContents = " + commentContents)

                <%--    // 수정 입력창을 생성하고 기존 내용을 채움--%>
                <%--    const form = document.createElement('div');--%>
                <%--    form.classList.add('update-comment-form');--%>
                <%--    form.innerHTML = `--%>
                <%--    <input type="text" class="form-control update-comment-writer" value="${commentWriter}" readonly>--%>
                <%--    <input type="text" class="form-control update-comment-contents" value="${commentContents}">--%>
                <%--    <button type="button" class="btn btn-primary update-comment-submit">수정 완료</button>--%>
                <%--`;--%>

                const form = document.createElement('div');
                form.classList.add('updatejung');
                form.innerHTML = '<input type="hidden" id="update-id" value="' + commentId + '">'
                form.innerHTML += '<input type="text" class="form-control update-comment-writer" value="' + commentWriter + '" readonly>'
                form.innerHTML += '<input type="text" id="update-contents" value="' + commentContents + '">'
                form.innerHTML += '<button type="button" id="Updatebtn" value="update-contents" onclick="UpdateGo()">수정 완료</button>'
                // form.innerHTML += '<input type="submit" value="update-contents" onblur="UpdateGo()">'
                // 현재 클릭한 수정 버튼의 부모 요소인 <td> 뒤에 입력창 추가
                button.parentElement.insertAdjacentElement('afterend', form);
            });
        });
    }

    //수정창을 없앰
    const remove1 = () => {
        // document.querySelectorAll('.pdldyd').forEach(button => {
        document.querySelectorAll('.updatejung').forEach(form => {
            // 이전에 열려있던 모든 수정 창을 닫음
            form.remove();
        });
        // button.innerHTML = "";
        // button.addEventListener('click', () => {
        //     const form = document.createElement('div');
        //     form.classList.add('updatejung');
        //     form.innerHTML = "";
        // });
        // });
    }

    document.addEventListener('DOMContentLoaded', () => {
        attachUpdateEventHandlers();
    });
    function attachUpdateEventHandlers() {
        document.querySelectorAll('.pdldyd').forEach(button => {
            button.addEventListener('click', handleUpdate);
        });
    }
    // 수정버튼 클릭시 작성자 확인
    function handleUpdate() {
        const button = this;
        const commentId = button.parentElement.parentElement.getAttribute('data-commentid');
        $.ajax({
            type: "POST",
            url: "/comment/UpdateCheck",
            data: {
                commentId: commentId
            },
            dataType: "text",
            success: function (res) {
                // remove1();
                if (res === "Same") {
                    //console.log("11111111")
                    updateForm();
                } else{
                    remove1();
                    alert("댓글 작성자가 아닙니다.")
                }
            },
            error: function (request, status, error) {
                console.log("code: " + request.status)
                console.log("message: " + request.responseText)
                console.log("error: " + error);
            }
        });
    }

    const UpdateGo = () => {
        const result1 = document.getElementById("update-contents").value;
        const id1 = document.getElementById("update-id").value;
        const board = '${board.id}';
        console.log(result1);
        console.log(id1);
        $.ajax({
            type: "post",
            url: "/comment/update",
            data: {
                commentId: id1,
                commentContents: result1,
                boardId: board
            },
            dataType: "json",
            success: function (commentList) {
                console.log("작성성공");
                updateCommentList(commentList);
                attachDeleteEventHandlers();
                attachUpdateEventHandlers();
            },
            error: function (request, status, error) {
                console.log("code: " + request.status)
                console.log("message: " + request.responseText)
                console.log("error: " + error);
            }
        });
    }



    // function UpdateRe() {
    //     console.log("123123")
    //     const button = this;
    //     const result1 = button.parentElement.getAttribute("update-contents").value();
    //     console.log(result1);
    // }
    // function attachUpdateEventHandlers() {
    //     document.querySelectorAll('.UpdateGo').forEach(button => {
    //         button.addEventListener('click', UpdateRe);
    //     });
    // }
    // document.querySelectorAll('.btnbtn').forEach(button => {
    //     button.addEventListener('click', () => {
    //         // 특정 값을 가져오는 로직
    //         // 기능함
    //         const commentId = button.parentElement.parentElement.getAttribute('data-commentid');
    //         const commentWriter = button.parentElement.parentElement.querySelector('td[data-commentWriter]').textContent;
    //         const commentContents = button.parentElement.parentElement.querySelector('td[data-commentContents]').textContent;
    //         console.log("commentId = " + commentId + ", commentWriter = " + commentWriter + ", commentContents = " + commentContents)
    //
    //
    //         var form = '<div class="update-comment-form">';
    //         form += '<input type="text" class="form-control update-comment-contents" value="' + commentContents + '">';
    //         form += '<button type="button" class="btn btn-primary update-comment-submit">수정 완료</button>';
    //         form += '</div>';
    //     });
    // });
    // $(document).on('click', '.comment-update-btn', function() {
    //     var commentId = $(this).closest('tr').data('commentid');
    //     var commentContentsElement = $(this).closest('tr').find('td:eq(1)');
    //     var commentContents = commentContentsElement.text().trim();
    //
    //     var form = '<div class="update-comment-form">';
    //     form += '<input type="text" class="form-control update-comment-contents" value="' + commentContents + '">';
    //     form += '<button type="button" class="btn btn-primary update-comment-submit">수정 완료</button>';
    //     form += '</div>';
    //
    //     $(this).closest('tr').append(form);
    //     $(this).hide();
    // });

    // const updateCheck2 = () => {
    //     const id = document.getElementById("updateCheck").value;
    //     $.ajax({
    //         type: "POST",
    //         url: "/comment/update2",
    //         data:{
    //             commentId: id
    //         },
    //         dataType:"text",
    //         success: function (res) {
    //             if (res === "1111") {
    //                 alert("딩동");
    //             } else {
    //                 alert("땡");
    //             }
    //         },
    //         error: function (request, status, error) {
    //             console.log("code: " + request.status)
    //             console.log("message: " + request.responseText)
    //             console.log("error: " + error);
    //         }
    //     });
    // }

    // 페이지 로드 시 삭제 버튼에 이벤트 핸들러 등록
    // ページロードじにさくじょボタンにイベントハンドラをとうろく


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
                attachUpdateEventHandlers();
            },
            error: function (request, status, error) {
                console.log("code: " + request.status)
                console.log("message: " + request.responseText)
                console.log("error: " + error);
            }
        });
    }

    document.addEventListener('DOMContentLoaded', () => {
        attachDeleteEventHandlers();
    });
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
        const commentId = button.parentElement.parentElement.getAttribute('data-commentid');
        $.ajax({
            type: "POST",
            url: "/comment/DeleteCheck2",
            data: {
                commentId: commentId
            },
            dataType: "text",
            success: function (res) {
                if (res === "Matched") {
                    $.ajax({
                        type: "POST",
                        url: "/comment/Delete3",
                        data: {
                            commentId: commentId,
                            boardId: '${board.id}'
                        },
                        dataType: "json",
                        success: function (commentList) {
                            alert("댓글 삭제 성공하였습니다.");
                            updateCommentList(commentList);
                            // 삭제 버튼에 대한 클릭 이벤트 핸들러 다시 등록
                            attachDeleteEventHandlers();
                            attachUpdateEventHandlers();
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


    const updateCommentList = (commentList) => {
        let output = "<table>";
        output += "<tr>";
        output += "<th>작성자</th>";
        output += "<th>내용</th>";
        output += "<th>작성시간</th>";

        commentList.forEach(comment => {
            output += "<tr data-commentid=" + comment.commentid + ">";
            output += "<td data-commetnWriter=" + comment.commentWriter + ">" + comment.commentWriter + "</td>";
            output += "<td data-commetnWriter=" + comment.commentContents + ">" + comment.commentContents + "</td>";
            output += "<td>" + comment.commentCreatedTime + "</td>";
            output += '<td><button class="comment-delete-btn">댓글 삭제</button></td>';
            output += '<td><button class="pdldyd">댓글 수정</button></td>';
            output += "</tr>";
        });

        output += "</table>";

        document.getElementById('comment-list').innerHTML = output;
        document.getElementById('commentWriter').value = '${loginEmail}';
        document.getElementById('commentContents').value = '';
    };
</script>
</body>
</html>
