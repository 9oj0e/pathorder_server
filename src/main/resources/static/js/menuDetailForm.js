// rendering
function render(data, sessionStoreId, menuId) {
    let html = ``;

    html += `
<form action="/stores/${sessionStoreId}/menus/${menuId}" method="post" id="menuEditForm" data-menu-id="${menuId}"data-store-id="${sessionStoreId}">
    <div style="display: grid; grid-template-columns: 1fr 2fr; grid-column-gap: 5%; padding: 30px">
        <div class="card" style="width:300px; height: 500px">
            <div class="card-header">
                <input type="file" id="editImg" class="form-control hidden-edt" accept="upload/*" name="encodedFile">
            </div>
            <div class="card-body" style="display: flex; height: 240px; justify-content: center; align-items: center">
                <div style="width: 160px">
                    <img id="cardImg" class="card-img" src="${data.body.imgFilePath}">
                </div>
            </div>
            <div id="menu" class="card-footer">
                <table class="table">
                    <tbody>
                    <tr>
                        <th>분류</th>
                        <td><input type="text" value="${data.body.category}" name="category" readonly/></td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" value="${data.body.name}" name="name"  readonly/></td>
                    </tr>
                    <tr>
                        <th>가격</th>
                        <td><input type="text" value="${data.body.price}" name="price" readonly/></td>
                    </tr>
                    <tr>
                        <th>설명</th>
                        <td><input type="text" value="${data.body.description}" name="description" readonly/></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div>
            <div style="display: flex; justify-content: end">
                <div id="inputStatusChangeBtns">
                    <div class="edit-btn" id="editBtn">
                        <button type="button" class="btn btn-outline-dark">
                            수정
                        </button>
                    </div>
                    <button type="submit" id="submitBtn" class="btn btn-outline-dark hidden" data-menu-id="${menuId}" data-edit-target="${menuId}"  data-store-id="${sessionStoreId}">
                        저장
                    </button>
                    <button type="button" id="cancelBtn" class="btn btn-outline-danger hidden">
                        취소
                    </button>
                </div>
            </div>
            <table class="table option">
                <thead>
                <tr>
                    <th>
                        <h4>필수 옵션</h4>
                    </th>
                    <td></td>
                    <td id="addRqOpt" class="add-opt-btn hidden-edt">
                        +
                    </td>
                </tr>
                </thead>
                <tbody id="required-menu-option">`;
    for (let i = 0; i < data.body.menuOptionList.length; i++) {
        let option = data.body.menuOptionList[i];
        if (option.required === true) {
            html += `
                <tr>
                    <input type="hidden" value="true" name="optionRequired"/>
                    <th>
                        <input type="text" value="${option.name}" name="optionName" readonly required/>
                    </th>
                    <td>
                        <input type="text" value="${option.price}" name="optionPrice" readonly required/>
                    </td>
                    <td class="del-opt-btn hidden-edt">
                        -
                    </td>
                </tr>`;
        }
    }
    html += `       
                </tbody>
            </table>
            <table class="table option mb-5">
                <thead>
                <tr>
                    <th>
                        <h4>선택 옵션</h4>
                    </th>
                    <td></td>
                    <td id="addOpt" class="add-opt-btn hidden-edt">
                        +
                    </td>
                </tr>
                </thead>
                <tbody id="optional-menu-option">`;
    for (let i = 0; i < data.body.menuOptionList.length; i++) {
        let option = data.body.menuOptionList[i];
        if (option.required === false) {
            html += `
                <tr>
                    <input type="hidden" value="false" name="optionRequired"/>
                    <th>
                        <input type="text" value="${option.name}" name="optionName" readonly required/>
                    </th>
                    <td>
                        <input type="text" value="${option.price}" name="optionPrice" readonly required/>
                    </td>
                    <td class="del-opt-btn hidden-edt">
                        -
                    </td>
                </tr>`;
        }
    }

    html += `
                </tbody>
            </table>
        </div>
    </div>
</form>`;
    return html;
}
// base64 converter
const convertBase64 = (file) => {
    return new Promise((resolve, reject) => {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(file);
        fileReader.onload = () => {
            resolve(fileReader.result);
        };
        fileReader.onerror = (error) => {
            reject(error);
        };
    });
};
// img upload
const uploadImage = async (event) => {
    const file = event.target.files[0];
    const base64 = await convertBase64(file);
    const cardImg = document.getElementById("cardImg");
    cardImg.src = base64;
};

// 메뉴 수정 버튼
$(document).on("click", "#editBtn", function () {
    $(this).addClass("hidden");
    $("#submitBtn").removeClass("hidden");
    $("#cancelBtn").removeClass("hidden");
    $("#editImg").removeClass("hidden-edt");
    $(".add-opt-btn").each(function () {
        $(this).removeClass("hidden-edt")
    })
    $(".del-opt-btn").each(function () {
        $(this).removeClass("hidden-edt")
    })
    $("input").each(function () {
        // let isReadOnly = $(this).prop('readOnly');
        $(this).prop('readonly', false);
        $(this).addClass("input-mode")
    });
});

// 메뉴 수정 취소
$(document).on("click", "#cancelBtn", function () {
    // 버튼 초기화
    $(this).removeClass("hidden");
    $("#submitBtn").addClass("hidden");
    $("#cancelBtn").addClass("hidden");
    $("#editImg").addClass("hidden-edt");
    $(".add-opt-btn").each(function () {
        $(this).addClass("hidden-edt")
    })
    $(".del-opt-btn").each(function () {
        $(this).addClass("hidden-edt")
    })
    $("input").each(function () {
        // let isReadOnly = $(this).prop('readOnly');
        $(this).prop('readonly', true);
        $(this).removeClass("input-mode")
    });
    // 문서 재 랜더링
    $('#menuForm').html(sessionStorage.getItem("menuDetail"));
});

// 이미지 preview
$(document).on("change", "#editImg", (event) => uploadImage(event))

// 필수 옵션 추가 버튼
$(document).on("click", "#addRqOpt", function () {
    let RqOpt = `
                <tr>
                    <input type="hidden" value="true" id="optionRequired" class="input-mode" name="optionRequired"/>
                    <th>
                        <input type="text" placeholder="옵션 이름" id="optionName" class="input-mode" name="optionName" required/>
                    </th>
                    <td>
                        <input type="text" placeholder="옵션 가격" id="optionPrice" class="input-mode" name="optionPrice" required/>
                    </td>
                    <td class="del-opt-btn">
                        -
                    </td>
                </tr>`;
    $("#required-menu-option").append(RqOpt);
})

// 선택 옵션 추가 버튼
$(document).on("click", "#addOpt", function () {
    let opt = `
                <tr>
                    <input type="hidden" value="false" id="optionRequired" class="input-mode" name="optionRequired"/>
                    <th>
                        <input type="text" placeholder="옵션 이름" id="optionName" class="input-mode" name="optionName" required/>
                    </th>
                    <td>
                        <input type="text" placeholder="옵션 가격" id="optionPrice" class="input-mode" name="optionPrice" required/>
                    </td>
                    <td class="del-opt-btn">
                        -
                    </td>
                </tr>`;
    $("#optional-menu-option").append(opt);
})

// 옵션 삭제 버튼
$(document).on("click", ".del-opt-btn", function () {
    $(this).parent().remove();
})

// 메뉴 수정
$(document).on("submit", "#menuEditForm", function (e) {
    e.preventDefault();
    let formData = new FormData(this);
    let menuOptions = [];
    for (let i = 0; i < formData.getAll("optionName").length; i++) {
        let option = {
            price: formData.getAll("optionPrice").at(i),
            name: formData.getAll("optionName").at(i),
            required: formData.getAll("optionRequired").at(i),
        }
        menuOptions.push(option);
    }
    let data = {
        price: formData.get("price"),
        name: formData.get("name"),
        encodedFile: convertBase64(formData.get("encodedFile")),
        category: formData.get("category"),
        description: formData.get("description"),
        menuOptions: menuOptions
    }
    let menuId = $(this).data('menu-id');
    let storeId = $(this).data('store-id');

    $.ajax({
        type: "PUT",
        url: `/stores/${storeId}/menus/${menuId}`,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (data) {
            let menuDetail = render(data, storeId, menuId);
            sessionStorage.setItem("menuDetail", menuDetail); // to reload
            $('#menuForm').html(menuDetail);
        },
        error: function (error) {
            console.error("Error fetching menu details: ", error);
            alert('메뉴 상세 정보를 가져오는데 실패했습니다.');
        }
    });
});