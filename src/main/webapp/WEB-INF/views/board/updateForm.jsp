<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

    <form>
        <input type="hidden" id="id" value="${board.id}"/>
        <div class="form-group">
            <input value="${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
        </div>


        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>

    </form>
        <button id="btn-update" class="btn btn-primary">수정</button>

</div>

<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>

<script type="text/javascript">
    let index = {
        init: function () {
            $("#btn-update").on("click", () => { // function(){} , ()=>{} this를 바인딩하기 위해서!!
                this.update();
            });
        },

        update: function () {
            let data = {
                title: $("#title").val(),
                content: $("#content").val()
            };

            console.log(id);
            console.log(data);

            $.ajax({
                type: "PUT",
                url: "/api/board/"+id,      //api/board로 data를 날린다.
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function (resp) {
                alert("글 수정이 완료되었습니다.");
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
