document.addEventListener("DOMContentLoaded", () => {
    const userEmail = document.getElementById("user_email");
    const domain = document.getElementById("domain-txt");
    const userPw1 = document.getElementById("user_pw1");
    const userPw2 = document.getElementById("user_pw2");
    const userName = document.getElementById("user_name");
    const telSecond = document.getElementById("tel_second");
    const telThird = document.getElementById("tel_third");

    const pwBtn = document.getElementById("pw_btn");
    const joinBtn = document.getElementById("join_btn");
    userPw1.addEventListener("change", checkPw);
    pwBtn.addEventListener("click", comparePw);
    telSecond.addEventListener("keyup", moveTel);
    joinBtn.addEventListener("click", validationChk);

    const domainListEl = document.querySelector("#domain-list");
    const domainInputEl = document.querySelector("#domain-txt");

    domainListEl.addEventListener("change", event => {
        if (event.target.value !== "type") {
            domainInputEl.value = event.target.value;
            domainInputEl.disabled = true;
        } else {
            domainInputEl.value = "";
            domainInputEl.disabled = false;
        }
    });

    function validationChk() {
        let idChk = /^[A-Za-z0-9_\.\-]/;
        let domainChk = /^[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

        if (userEmail.value == "") {
            alert("이메일을 입력하세요.");
            userEmail.focus();
            return false;
        }

        if (domain.value == "") {
            alert("이메일을 입력하세요.");
            domain.focus();
            return false;
        }

        if (userPw1.value == "") {
            alert("비밀번호를 입력하세요.");
            userPw1.focus();
            return false;
        }

        if (userPw2.value == "") {
            alert("비밀번호를 입력하세요.");
            userPw2.focus();
            return false;
        }

        if (userName.value == "") {
            alert("이름을 입력하세요.");
            userName.focus();
            return false;
        }

        if (telSecond.value == "") {
            alert("전화번호를 입력하세요.");
            telSecond.focus();
            return false;
        }

        if (telThird.value == "") {
            alert("전화번호를 입력하세요.");
            telThird.focus();
            return false;
        }

        if (idChk.test(userEmail.value) == false) {
            alert("이메일형식이 올바르지 않습니다.");
            userEmail.focus();
            return false;
        }

        if (domainChk.test(domain.value) == false) {
            alert("이메일형식이 올바르지 않습니다.");
            userEmail.focus();
            return false;
        }

        if (userPw1.value.length < 4) {
            alert("사용할 수 없는 비밀번호입니다. 4자 이상 입력해 주세요.");
            userPw1.focus();
            return false;
        }
        moveToConfirm();
    }

    function checkPw() {
        if (userPw1.value.length < 4) {
            alert("사용할 수 없는 비밀번호입니다. 4자 이상 입력해 주세요.");
            userPw1.select();
        }
    }

    function comparePw() {
        if (userPw1.value == "") {
            alert("비밀번호를 입력하세요.");
            userPw1.value = "";
            userPw1.focus();
            return false;
        }

        if (userPw2.value == "") {
            alert("비밀번호를 입력하세요.");
            userPw2.value = "";
            userPw2.focus();
            return false;
        }

        if (userPw1.value.length < 4) {
            alert("사용할 수 없는 비밀번호입니다. 4자 이상 입력해 주세요.");
            userPw1.value = "";
            userPw1.focus();
            return false;
        }

        if (userPw1.value !== userPw2.value) {
            alert("비밀번호가 일치하지 않습니다.");
            userPw1.value = "";
            userPw2.value = "";
        } else {
            alert("사용 가능한 비밀번호입니다.");
        }
    }

    const birthYearEl = document.querySelector("#birth-year");
    isYearOptionExisted = false;
    birthYearEl.addEventListener("focus", function () {
        if (!isYearOptionExisted) {
            isYearOptionExisted = true;
            for (var i = 1940; i <= 2022; i++) {
                const YearOption = document.createElement("option");
                YearOption.setAttribute("value", i);
                YearOption.innerText = i;
                this.appendChild(YearOption);
            }
        }
    });
    const birthMonthEl = document.querySelector("#birth-month");
    isMonthOptionExisted = false;
    birthMonthEl.addEventListener("focus", function () {
        if (!isMonthOptionExisted) {
            isMonthOptionExisted = true;
            for (var i = 1; i <= 12; i++) {
                const MonthOption = document.createElement("option");
                MonthOption.setAttribute("value", i);
                MonthOption.innerText = i;
                this.appendChild(MonthOption);
            }
        }
    });
    const birthDayEl = document.querySelector("#birth-day");
    isDayOptionExisted = false;
    birthDayEl.addEventListener("focus", function () {
        if (!isDayOptionExisted) {
            isDayOptionExisted = true;
            for (var i = 1; i <= 31; i++) {
                const DayOption = document.createElement("option");
                DayOption.setAttribute("value", i);
                DayOption.innerText = i;
                this.appendChild(DayOption);
            }
        }
    });

    YearOption.setAttribute("value", i);

    const selectedYearEl = document.querySelector("#print-date");
    birthYearEl.addEventListener("change", event => {
        selectedYearEl.textContent = `Year of birth : ${event.target.value}`;
    });
    const selectedMonthEl = document.querySelector("#print-date");
    birthMonthEl.addEventListener("change", event => {
        selectedMonthEl.textContent = `Month of birth : ${event.target.value}`;
    });
    const selectedDayEl = document.querySelector("#print-date");
    birthDayEl.addEventListener("change", event => {
        selectedDayEl.textContent = `Day of birth : ${event.target.value}`;
    });

    function moveTel() {
        if (telSecond.value.length >= 4) {
            telThird.focus();
        }
    }
});

function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            var roadAddr = data.roadAddress;
            var extraRoadAddr = "";

            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            if (data.buildingName !== "" && data.apartment === "Y") {
                extraRoadAddr +=
                    extraRoadAddr !== "" ? ", " + data.buildingName : data.buildingName;
            }
            if (extraRoadAddr !== "") {
                extraRoadAddr = " (" + extraRoadAddr + ")";
            }

            document.getElementById("sample4_postcode").value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

            if (roadAddr !== "") {
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = "";
            }

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
        },
    }).open();
}

// function moveToConfirm() {
//     location.replace("./join_check.html");
// }