document.addEventListener("DOMContentLoaded", () => {
    const totalTerms = document.getElementById("total_terms");
    const termsEls = document.querySelectorAll(".terms_el");
    const terms01 = document.getElementById("terms_01");
    const terms02 = document.getElementById("terms_02");
    const agreeBtn = document.getElementById("agreeBtn");

    totalTerms.addEventListener("click", totalCheck);
    terms01.addEventListener("click", termsCheck);
    terms02.addEventListener("click", termsCheck);
    agreeBtn.addEventListener("click", fregister_submit);

    function totalCheck() {
        if (totalTerms.checked === true) {
            termsEls.forEach(termsEl => {
                termsEl.checked = true;
            });
        } else {
            termsEls.forEach(termsEl => {
                termsEl.checked = false;
            });
        }
    }

    function termsCheck() {
        if (terms01.checked && terms02.checked) {
            totalTerms.checked = true;
        } else {
            totalTerms.checked = false;
        }
    }

    function fregister_submit(f) {
        if (terms_01.checked === false) {
            alert("이용약관에 동의해야 회원가입 하실 수 있습니다.");
            event.preventDefault();
        }

        if (terms_02.checked === false) {
            alert("개인정보 수집 및 이용에 동의해야 회원가입 하실 수 있습니다.");
            event.preventDefault();
        }
    }
});