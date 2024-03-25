<!-- 회원가입용입니다. かいいんとうろくようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save</title>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
<h2>회원가입</h2>
    <form action="/member/saveForMember" method="post" name="saveForm" onsubmit="return checkSave();">
        <input type="text" name="memberEmail" placeholder="이메일" id="memberEmail" onblur="emailCheckForMember()">
        <!-- onblur는 입력창을 벗어났을때 함수를 호출하도록 하는 이벤트 처리 방식 -->
        <!-- onblurはにゅうりょくウィンドウをはずれたときにかんすうをよびだすようにするイベントしょりほうしき -->
        <p id="check-result"></p>
        <input type="text" name="memberPassword" placeholder="비밀번호"></br>
        <input type="text" name="memberName" placeholder="이름"></br>
        <input type="text" name="memberAge" placeholder="나이"></br>
        <input type="text" name="memberMobile" placeholder="전화번호"></br>
        <input type="submit" value="회원가입">
    </form>
</body>
<script>
    // 이메일 입력값을 가져오고
    // 입력값을 서버로 전송하고 똑같은 이메일이 있는지 체크한 후
    // 사용 가능 여부를 이메일 입력창 아래에 표시
    // IDにゅうりょくちをしゅとくし
    // にゅうりょくちをサーバにそうしんして、おなじIDがあるかをチェックしたあと
    // しようかのうかどうかをIDのにゅうりょくウィンドウのしたにひょうじ
    const emailCheckForMember = () => {
        const email = document.getElementById("memberEmail").value;
        // 입력한 memberEmail 값을 가져옴
        // にゅうりょくしたmemberEmailちをしゅとくする
        const checkResult = document.getElementById("check-result");
        // 출력을 위한 요소인 <p>에 있는 값을 가져옴
        // しゅつりょくのためのようそである<p>にあるちをしゅとくする
        console.log("입력한 이메일", email);
        $.ajax({
            // ajax는 화면이 바뀜없이 요청을 주고 받기 위함
            // ajaxはがめんがかわることなくようせいをやりとりするための
            // 요청방식 : post, url : "email-check", 데이터 : email
            // ようせいほうしき : post, url : "email-check", データ : email
            type : "post",
            url : "/member/email-check",
            async : false,
            data : {
                "memberEmail" : email
            },
            success: function(res) {
                console.log("요청 성공", res);
                if (res == "nooneuse") {
                    console.log("사용 가능한 이메일 입니다.");
                    checkResult.style.color = "green";
                    checkResult.innerHTML = "사용 가능한 이메일 입니다.";
                } else {
                    sameEmail = res;
                    console.log("이미 사용중인 이메일 입니다.");
                    checkResult.style.color = "red";
                    checkResult.innerHTML = "이미 사용 중인 이메일 입니다.";

                }
            },
            error: function (err) {
                console.log("에러발생", err);
                // 이 내용이 보인다는 것은 주고 받는 것이 잘 되지 않았다는 것
                // このないようがみえるということは、やりとりがうまくいかなかったということ
            }
        });
    }

    const checkSave = () => {
        let saveinputs = document.saveForm;
        if (!saveinputs.memberEmail.value) {
            alert("아이디를 입력해주세요.")
            return false;
        }
        if (!saveinputs.memberPassword.value) {
            alert("비밀번호를 입력해주세요.")
            return false;
        }
        if (!saveinputs.memberName.value) {
            alert("이름을 입력해주세요.")
            return false;
        }
        if (!saveinputs.memberAge.value) {
            alert("나이를 입력해주세요.")
            return false;
        }
        if (!saveinputs.memberMobile.value) {
            alert("전화번호를 입력해주세요.")
            return false;
        }
        if (sameEmail !== "ok") {
            alert("중복된 아이디 입니다.")
            return false;
        }
    }
</script>
</html>









