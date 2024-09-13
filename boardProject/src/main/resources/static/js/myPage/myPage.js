/* 다음 주소 API로 주소 검색하기 */
function findAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
//            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
/*            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }*/

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}

/* 주소 검색 버튼 클릭 시 */
document.querySelector("#findAddressBtn").addEventListener("click", findAddress);

// 함수명만 작성하는 경우 
// 함수명() 작성하는 경우 : 함수에 정의된 내용 실행


// -------------------------------------------------------------------




// -------------------------------------------------------------------

/* ============= 유효성 검사(Validation) ============= */

// 입력 값이 유효한 형태인지 표시하는 객체(체크리스트)
const checkObj = {
    "memberNickname" : true
}

/* 닉네임 검사
    - 3글자 이상
    - 중복 X
*/
const memberNickname = document.querySelector("#memberNickname");
memberNickname.addEventListener("input", ()=>{
    // input이벤트 : 입력과 관련된 모든 동작 (JS를 이용한 값세팅 제외)

    // 입력된 값 얻어오기(양쪽 공백 제거)
    const inputValue = memberNickname.value.trim();

    if (inputValue.length<3) { //3글자 미만

        // 클래스 제거
        memberNickname.classList.remove("green");
        memberNickname.classList.remove("red");

        // 닉네임이 유효하지 않다고 기록
        checkObj.memberNickname=false;

        return;
    }

    // 비동기로 입력된 닉네임이 데이터 베이스에 존재하는지 확인하는 Ajex코드(fetch()API)작성

    //get방식 요청 (쿼리스트링으로 파라미터 전달)
    fetch("/myPage/checkNickname?input=" + inputValue)
    .then(response => {
        if(response.ok){ // 응답 상태코드 200(성공)인 경우
          return response.text(); // 응답 결과를 text형태로 변환 
        }
        throw new Error("중복 검사 실패 : " + response.status);
    }) 
    .then(result => {
        // result == 첫 번째 then에서 return된 값
    
        if(result > 0){ // 중복인 경우
          memberNickname.classList.add("red");
          memberNickname.classList.remove("green");
          checkObj.memberNickname = false; // 체크리스트에 false 기록
          return;
        }
    
        // 중복이 아닌 경우
        memberNickname.classList.add("green");
        memberNickname.classList.remove("red");
        checkObj.memberNickname = true; // 체크리스트에 true 기록
    
      })
      .catch(err => console.error(err));
    
    
    });
