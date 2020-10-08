<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form>
        <div class="form-group">
            <input type="hidden" id="id" value="${principal.user.id}"/>
            <label for="username">Username</label>
            <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" value="${principal.user.email}"class="form-control" placeholder="Enter email" id="email">
        </div>

    </form>
    <button id="btn-update" class="btn btn-primar y">회원 수정</button>

</div>


<script type="text/javascript">
    let index = {
        init: function () {
            $("#btn-update").on("click", () => { // function(){} , ()=>{} this를 바인딩하기 위해서!!
                this.update();
            });
        },

        update: function () {
          //  alert('user의 save함수 호출됨');
            let data = {
                id: $("#id").val(),
                password: $("#password").val(),
                email: $("#email").val()
            };
         //   console.log(data);

            //ajax 호출시 default가 비동기 호출
            //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
            $.ajax({
               //회원가입 수행 요청
                type: "PUT",
                url: "/user",
                data: JSON.stringify(data), //http body 데이터
                contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
                dataType: "json" //요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(Stirng)
                                //생긴게 json이라면 javascript 오브젝트로 변경
            }).done(function (resp) {
                 alert("회원 정보 수정이 완료되었습니다.");
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