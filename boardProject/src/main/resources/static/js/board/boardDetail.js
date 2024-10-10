// 현재 상세 조회한 게시글 번호
const boardNo = location.pathname.split("/")[3];

/* 좋아요 하트 클릭시 */
const boardLike = document.querySelector("#boardLike");
boardLike.addEventListener("click",e=>{

    // 1. 로그인 여부 검사
    if(loginCheck === false){
        alert("로그인 후 이용해 주세요");
        return;
    }

    // 2. 비동기로 좋아요 요청
    fetch("/board/like",{
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body :  boardNo
    })
    .then(Response=>{
        if(Response.ok) return Response.json();
        throw new Error("좋아요 실패");
    })
    .then(result=>{
        // console.log("result : ", result);

        // 좋아요 결과가 담긴 result 객체의 check값에 따라
        // 하트 아이콘 비우기/채우기 지정
        if(result.check==='insert'){ //채우기
            boardLike.classList.add("fa-solid");
            boardLike.classList.remove("fa-regular");
        } else{ //비우기
            boardLike.classList.add("fa-regular");
            boardLike.classList.remove("fa-solid");
        }
    // 좋아요 하트 다음 형제 요소의 내용을 result.count로 변경
    boardLike.nextElementSibling.innerText = result.count;


    })
    .catch(err=>console.error(err));

})

//-------------------------------------------------------------------------------


/* * 삭제버튼 클릭시 *
    - 삭제버튼 클릭시 "정말 삭제하시겠습니까?" confirm()

    - /editBoard/delete, POST 방식, 동기식 요청
     -> form태그 생성 + 게시글 번호가 세팅된 input
     -> body태그 제일 아래 추가해서 submit()

    - 서버(java)에서 로그인 한 회원의 번호를 얻어와 
      로그인 한 회원이 작성한 글이 맞는지 SQL에서 검사
*/

// 삭제 버튼 요소 얻어오기
const deleteBtn = document.querySelector("#deleteBtn");

// 삭제버튼이 존재할 때 이벤트 리스너 추가
deleteBtn?.addEventListener("click",()=>{

    if(confirm("찐삭제?")==false){
        return;
    }

    const url = "/editBoard/delete" // 요청주소
    // 게시글 번호 == 전역변수 boardNo

    // form 태그 생성
    const form = document.createElement("form");
    form.action = url;
    form.method = "POST";

    // input 태그 생성
    const input = document.createElement("input");
    input.type = "hidden";
    input.name = "boardNo";
    input.value = boardNo;

    form.append(input); // form 자식으로 input 추가

    // body 자식으로 form 추가
    document.querySelector("body").append(form);

    form.submit(); // 제출

})

//----------------------------------------------------------------

/* * 수정버튼 클릭시 *
    - /editBoard/{boardCode}/{boardNo}, POST 방식, 동기식 요청
     -> form태그 생성 + 게시글 번호가 세팅된 input
     -> body태그 제일 아래 추가해서 submit()

    - 서버(java)에서 로그인 한 회원의 번호를 얻어와 
      로그인 한 회원이 작성한 글이 맞는지 SQL에서 검사 후
      맞을 경우 해당 글을 상세조회 한 후 수정 화면으로 전환
*/

    const updateBtn = document.querySelector("#updateBtn");

    updateBtn?.addEventListener("click",()=>{
    // form 태그 생성
    const form = document.createElement("form");

    // /editBoard/{boardCode}/{boardNo}/updateView
    form.action = location.pathname.replace("board","editBoard") + "/updateView";
    form.method = "POST";

    document.querySelector("body").append(form);
    form.submit();

    })
