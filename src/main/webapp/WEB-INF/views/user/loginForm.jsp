<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>


        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
    <%--    <div class="form-group form-check">
            <label class="form-check-label">
                <input name="remember " class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>--%>
        <button id="btn-login" class="btn btn-primary">로그인</button>
    </form>

</div>

<script type="text/javascript">

    let index = {
        init: function () {
            $("#btn-login").on("click", () => { // function(){} , ()=>{} this를 바인딩하기 위해서!!
                this.login();
            });
        },

        login: function () {
            //  alert('user의 save함수 호출됨');
            let data = {
                username: $("#username").val(),
                password: $("#password").val(),
            };
            //   console.log(data);

            //ajax 호출시 default가 비동기 호출
            //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
            $.ajax({
                //회원가입 수행 요청
                type: "POST",
                url: "/api/user/login",
                data: JSON.stringify(data), //http body 데이터
                contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
                dataType: "json" //요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(Stirng)
                //생긴게 json이라면 javascript 오브젝트로 변경
            }).done(function (resp) {
                alert("로그인이 완료되었습니다.");
                console.log(resp);
                location.href = "/";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }

    index.init();
</script>

<%@ include file="../layout/footer.jsp"%>
