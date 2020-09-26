<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

    <form>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter title" id="title">
        </div>


        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>

    </form>
        <button id="btn-save" class="btn btn-primary">글쓰기</button>

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
            $("#btn-save").on("click", () => { // function(){} , ()=>{} this를 바인딩하기 위해서!!
                this.save();
            });
        },

        save: function () {
            let data = {
                title: $("#title").val(),
                content: $("#content").val()
            };

            $.ajax({
                type: "POST",
                url: "/api/board",      //api/board로 data를 날린다.
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function (resp) {
                alert("글쓰기가 완료되었습니다.");
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
