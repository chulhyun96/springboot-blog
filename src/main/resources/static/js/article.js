// 수정기능
const modifyButton = document.getElementById('modify-btn')

if (modifyButton) {
    modifyButton.addEventListener('click', ev => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.')
                location.replace(`/articles/${id}`)
            })
    })
}
// 삭제기능
const deleteButton = document.getElementById("delete-btn")

if (deleteButton) {
    deleteButton.addEventListener('click', ev => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료 되었습니다.')
                location.replace('/articles')
            })
    })
}

// 등록
const createButton = document.getElementById('create-btn')

if (createButton) {
    createButton.addEventListener('click', ev => {
        fetch("/api/articles", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        })
            .then(() => {
                alert("등록이 완료되었습니다.");
                location.replace("/articles");
            });
    });
}
