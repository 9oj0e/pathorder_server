document.addEventListener('DOMContentLoaded', function() {
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress;
                var extraRoadAddr = "";

                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraRoadAddr += data.bname;
                }
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if (extraRoadAddr !== '') {
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                document.getElementById('postcode').value = data.zonecode;
                document.getElementById('roadAddress').value = roadAddr;
                document.getElementById('jibunAddress').value = data.jibunAddress;
                document.getElementById('extraAddress').value = extraRoadAddr;

                var fullAddress = roadAddr !== '' ? roadAddr + extraRoadAddr : data.jibunAddress;
                document.getElementById('storeAddress').value = fullAddress;

                getCoordinates(fullAddress);
            }
        }).open();
    }

    function getCoordinates(address) {
        console.log("주소로 좌표를 찾습니다:", address);

        if (typeof daum === 'undefined' || typeof daum.maps === 'undefined' || typeof daum.maps.services === 'undefined') {
            console.error("Kakao Maps API가 로드되지 않았습니다. API 키와 네트워크 상태를 확인하세요.");
            return;
        }

        var geocoder = new daum.maps.services.Geocoder();

        geocoder.addressSearch(address, function(result, status) {
            if (status === daum.maps.services.Status.OK) {
                var coords = new daum.maps.LatLng(result[0].y, result[0].x);

                console.log("위도: " + coords.getLat());
                console.log("경도: " + coords.getLng());

                var mapContainer = document.getElementById('map'),
                    mapOption = {
                        center: new daum.maps.LatLng(coords.getLat(), coords.getLng()),
                        level: 3
                    };

                var map = new daum.maps.Map(mapContainer, mapOption);

                var marker = new daum.maps.Marker({
                    map: map,
                    position: coords
                });
            } else {
                console.error("주소 검색에 실패했습니다: " + status);
            }
        });
    }

    window.execDaumPostcode = execDaumPostcode;
});
