$(document).ready(function() {
    var baseUrl = "/api";
    var isModified = false;
    var adList = [];
    var modifiedAd = {};

    $.ajax({
        url: baseUrl + "/advertisements",
        type: 'get',
        success: function(data) {
            adList = data;
            var html = '';
            for (var i = 0; i < data.length; i++) {
                html += (
                    "<tr class=\"ad-row\" data-id=\"" + (data[i].id) + "\">\n" +
                    "            <td>" + data[i].name + "</td>\n" +
                    "            <td>" + data[i].startTime + "</td>\n" +
                    "            <td>" + data[i].endTime + "</td>\n" +
                    "            <td><img class=\"ad_image\" src=\"" + baseUrl + "/advertisements/images?name=" + data[i].imageUrl + "\"></td>\n" +
                    "            <td>" + data[i].linkUrl + "</td>\n" +
                    "            <td>\n" +
                    "              <button type=\"button\" class=\"btn btn-danger delete-ad-button\" data-id=\"" + data[i].id + "\">삭제</button>\n" +
                    "            </td>\n" +
                    "          </tr>"
                );

            }

            $('#ad-rows').html(html);
        },
        error: function(data) {
            console.log(data);
        }
    });

    $('#create-ad-button').click(function () {
        isModified = false;
        $('#name').val('');
        $('#startTime').val('');
        $('#endTime').val('');
        $('#linkUrl').val('');
        $('#image').val('');
        $('#createAd').modal('show');
    });

    $('#image').change(function(e) {
        $('#image-label').text($('#image')[0].files[0].name);
    });

    $('#submit-button').click(function() {
        var name = $('#name').val();
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();
        var image = $('#image')[0].files[0];
        var linkUrl = $('#linkUrl').val();

        if (!isModified) {
            if (name === '' || startTime === '' || endTime === '' || image === undefined || linkUrl === '') {
                alert('빈 값이 있습니다.');
                return;
            }

            var formData = new FormData();
            formData.append('name', name);
            formData.append('startTime', startTime);
            formData.append('endTime', endTime);
            formData.append('image', image);
            formData.append('linkUrl', linkUrl);

            $.ajax({
                url: baseUrl + "/advertisements",
                type: 'post',
                contentType: false,
                processData: false,
                data: formData,
                success: function (data) {
                    adList.push(data);
                    var html = $('#ad-rows').html();
                    html += (
                        "<tr class=\"ad-row\" data-id=\"" + (data.id) + "\">\n" +
                        "            <td>" + data.name + "</td>\n" +
                        "            <td>" + data.startTime + "</td>\n" +
                        "            <td>" + data.endTime + "</td>\n" +
                        "            <td><img class=\"ad_image\" src=\"" + baseUrl + "/advertisements/images?name=" + data.imageUrl + "\"></td>\n" +
                        "            <td>" + data.linkUrl + "</td>\n" +
                        "            <td>\n" +
                        "              <button type=\"button\" class=\"btn btn-danger delete-ad-button\" data-id=\"" + (data.id) + "\">삭제</button>\n" +
                        "            </td>\n" +
                        "          </tr>"
                    );

                    $('#ad-rows').html(html);
                    $('#createAd').modal('hide');
                },
                error: function(data) {
                    console.log(data);
                }
            });
        } else {
            if (name === '' || startTime === '' || endTime === '' || linkUrl === '') {
                alert('빈 값이 있습니다.');
                return;
            }

            if (image !== undefined) {
                alert('이미지는 수정할수없습니다.');
                $('#image-label').text('');
                $('#image').val('');
                return;
            }

            modifiedAd.name = name;
            modifiedAd.startTime = startTime;
            modifiedAd.endTime = endTime;
            modifiedAd.linkUrl = linkUrl;

            $.ajax({
                url: baseUrl + "/advertisements/ads/" + modifiedAd.id,
                type: 'put',
                contentType: 'application/json',
                data: JSON.stringify(modifiedAd),
                success: function (data) {
                    location.reload();
                },
                error: function(data) {
                    console.log(data);
                }
            });
        }
    });

    $('tbody').on('click', 'tr', function(e) {
        var index = findIndexById($(this).attr('data-id'));
        if (index == -1) {
            return;
        }
        isModified = true;
        modifiedAd = adList[index];

        $('#name').val(adList[index].name);
        $('#startTime').val(adList[index].startTime);
        $('#endTime').val(adList[index].endTime);
        $('#linkUrl').val(adList[index].linkUrl);
        $('#image').val('');

        $('#createAd').modal('show');
    });

    $('tbody').on('click', '.delete-ad-button', function() {
        var index = findIndexById($(this).attr('data-id'));
        console.log(index);
        if (index == -1) {
            return;
        }

        $.ajax({
            url: baseUrl + "/advertisements/ads/" + adList[index].id,
            type: 'delete',
            success: function() {
                location.reload();
            },
            error: function() {

            }
        })
        return false;
    });

    function findIndexById(id) {
        for (var i = 0; i < adList.length; i++) {
            if (adList[i].id == id)
                return i;
        }
        return -1;
    }
});