function page_move(url) {
    var form = document.createElement("form");

    form.action = url;
    form.method = "post";

    document.body.appendChild(form);
    form.submit();
}
function getManager()
{
    $.ajax({
        url: "/user/manager",
        type: "PUT",
        success: function(data){
            alert("관리자 권한을 받았습니다.");
            $(location).attr('href','/');
        },
        error: function(){
            alert("err");
        }
    });
}
function deleteRestaurant(rno)
{
    $.ajax({
        url: "/restaurant/delete/" + rno,
        type: "DELETE",
        success: function(data){
            alert("삭제 되었습니다");
            $(location).attr('href','/');
        },
        error: function(){
            alert("err");
        }
    });
}