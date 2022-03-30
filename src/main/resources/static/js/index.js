$(document).ready(function () {
   postShow();
})
// 전체 글 목록 조회
function postShow() {
    $.ajax({
        type: 'GET',
        url: '/api/postsInfo',
        success: function (response) {
            $('#post-table').empty();

            for (let i = 0; i < response.length; i++) {
                let post = response[i];
                let username = post.username.substring(0,6);
                let date = post.createdAt.split('T')[0] + " " + post.createdAt.split('T')[1].substring(0,8);
                let tempHtml = `<tr>
                                            <th scope="row">${post.id}</th>
                                            <td onclick="document.location.href = '/detailPostView?id=${post.id}'">${post.title}</td>
                                            <td>${post.usernickname}(${username}) </td>
                                            <td>${date}</td>
                                        </tr>`;
                $('#post-table').append(tempHtml);
            }
        }
    })
}

// 전체 삭제
function deleteAll() {
    $.ajax({
        type: "DELETE",
        url: `/api/posts`,
        success: function (response) {
            alert('글 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
}