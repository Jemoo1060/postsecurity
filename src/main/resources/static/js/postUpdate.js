
// 글 정보 수정하기
function updatePost() {
    let title = $('#title').val();
    let content = $('#content').val();

    if(title.length > 12 || title.length == 0){
        alert('제목은 1 ~ 12자리로 설정해주세요');
        $('#title').val('');
        $('#title').focus();
        return
    }
    else if(content.length == 0){
        alert('글 내용을 작성해주세요');
        $('#content').focus();
        return
    }

    $("form").submit();
}