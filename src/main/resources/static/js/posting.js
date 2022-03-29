
// 글 등록
function writePost() {
    let title = $('#title').val();
    let content = $('#content').val();

    if(title.length > 12 || title.length == 0){
        alert('제목은 1 ~ 12자리로 설정해주세요');
        $('#title').val('');
        $('#title').focus();
        return
    }
    else if(content.length == 0 || content.length > 255){
        alert('글 내용을 1 ~ 255자로 작성해주세요');
        $('#content').focus();
        return
    }

    let data = {'title': title, 'content': content};
    $.ajax({
        type: "POST",
        url: "/api/posts",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('글이 성공적으로 등록되었습니다.');
            window.location.href = '/';
        }
    });
}