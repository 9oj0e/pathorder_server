function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 도로명 주소
            var roadAddr = data.roadAddress;
            var extraRoadAddr = "";

            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 추가 주소가 있으면, 추가 주소를 괄호 안에 넣는다.
            if (extraRoadAddr !== '') {
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 입력한다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById('sample4_roadAddress').value = roadAddr;
            document.getElementById('sample4_jibunAddress').value = data.jibunAddress;
            document.getElementById('sample4_extraAddress').value = extraRoadAddr;

            // 기존에 입력되어 있던 주소 input 필드에 새로운 주소를 설정한다.
            // 도로명 주소가 있으면 도로명 주소를, 그렇지 않으면 지번 주소를 사용한다.
            var fullAddress = roadAddr !== '' ? roadAddr + extraRoadAddr : data.jibunAddress;
            document.getElementById('address').value = fullAddress;

            // 주소를 찾은 후 추가적인 안내 텍스트 박스를 처리한다.
            var guideTextBox = document.getElementById("guide");
            if (data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = "(예상 도로명 주소 : " + expRoadAddr + ")";
                guideTextBox.style.display = "block";
            } else if (data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = "(예상 지번 주소 : " + expJibunAddr + ")";
                guideTextBox.style.display = "block";
            } else {
                guideTextBox.innerHTML = "";
                guideTextBox.style.display = "none";
            }
        }
    }).open();
}