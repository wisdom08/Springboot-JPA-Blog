<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id == principal.user.id}">
    <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>

    <br/>
    <br/>

    <div>
        글 번호 : <span id="id"><i>${board.id} </i></span>
        작성자 : <span><i>${board.user.username} </i></span>
    </div>
    <br/>


        <div>
            <h3>${board.title}</h3>
        </div>

        <hr/>

        <div>
            <div>
                ${board.content}
            </div>
        </div>
        <hr/>

</div>

<script type="text/javascript">
    let index = {
        init: function () {
            $("#btn-delete").on("click", () => { // function(){} , ()=>{} this를 바인딩하기 위해서!!
                this.deleteById();
            });
        },

        deleteById: function () {
            let id = $("#id").text();


            $.ajax({
                type: "DELETE",
                url: "/api/board/"+id,      //api/board로 data를 날린다.
                dataType: "json"
            }).done(function (resp) {
                alert("삭제가 완료되었습니다.");
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
