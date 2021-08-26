App.getUser();

function getAllClass(){
    $.ajax({
        type: "GET",
        url: "/members/allClass"
    }).done(function (classes){
        let content = "";
        for (let i = 0; i < classes.length; i++) {
            content += `
                    <option value="${classes[i].class_id}">${classes[i].class_name}</option>
                `;
        }
        $("#classes").html(content);
    })
}

function getAllMember(){
    $.ajax({
        type: "GET",
        url: "/members/allMember"
    }).done(function (member){
        let content = "";
        for (let i = member.length-1; i >= 0; i-- ) {
            content += `
                        <tr id="row${member[i].member_id}">
                              <td>${member[i].member_name}</td>
                              <td>${member[i].phoneNumber}</td>
                              <td>${member[i].email}</td>
                              <td>${member[i].aclass.class_name}</td>
                              <td class="text-center">
                                <button value="${member[i].member_id}" class="btn btn-outline-primary mr-2 edit-button" ><i class="far fa-edit"></i>Sửa</button>
                                <button value="${member[i].member_id}" class="btn btn-outline-danger delete-button" ><i class="fas fa-trash-alt"></i>Xóa</button>
                              </td>
                        </tr>
                `;
        }
        $("#memberList tbody").html(content);
        $("#memberList").DataTable({
            columnDefs: [
                { orderable: false, targets: [4] },
                { searchable: false, targets: [4] }
            ],
            "language": {
                "processing": "Đang xử lý...",
                "infoFiltered": "(được lọc từ _MAX_ mục)",
                "aria": {
                    "sortAscending": ": Sắp xếp thứ tự tăng dần",
                    "sortDescending": ": Sắp xếp thứ tự giảm dần"
                },
                "autoFill": {
                    "cancel": "Hủy",
                    "fill": "Điền tất cả ô với <i>%d<\/i>",
                    "fillHorizontal": "Điền theo hàng ngang",
                    "fillVertical": "Điền theo hàng dọc"
                },
                "buttons": {
                    "collection": "Chọn lọc <span class=\"ui-button-icon-primary ui-icon ui-icon-triangle-1-s\"><\/span>",
                    "colvis": "Hiển thị theo cột",
                    "colvisRestore": "Khôi phục hiển thị",
                    "copy": "Sao chép",
                    "copyKeys": "Nhấn Ctrl hoặc u2318 + C để sao chép bảng dữ liệu vào clipboard.<br \/><br \/>Để hủy, click vào thông báo này hoặc nhấn ESC",
                    "copySuccess": {
                        "1": "Đã sao chép 1 dòng dữ liệu vào clipboard",
                        "_": "Đã sao chép %d dòng vào clipboard"
                    },
                    "copyTitle": "Sao chép vào clipboard",
                    "csv": "File CSV",
                    "excel": "File Excel",
                    "pageLength": {
                        "-1": "Xem tất cả các dòng",
                        "_": "Hiển thị %d dòng"
                    },
                    "pdf": "File PDF",
                    "print": "In ấn"
                },
                "infoThousands": "`",
                "select": {
                    "cells": {
                        "1": "1 ô đang được chọn",
                        "_": "%d ô đang được chọn"
                    },
                    "columns": {
                        "1": "1 cột đang được chọn",
                        "_": "%d cột đang được được chọn"
                    },
                    "rows": {
                        "1": "1 dòng đang được chọn",
                        "_": "%d dòng đang được chọn"
                    }
                },
                "thousands": "`",
                "searchBuilder": {
                    "title": {
                        "_": "Thiết lập tìm kiếm (%d)",
                        "0": "Thiết lập tìm kiếm"
                    },
                    "button": {
                        "0": "Thiết lập tìm kiếm",
                        "_": "Thiết lập tìm kiếm (%d)"
                    },
                    "value": "Giá trị",
                    "clearAll": "Xóa hết",
                    "condition": "Điều kiện",
                    "conditions": {
                        "date": {
                            "after": "Sau",
                            "before": "Trước",
                            "between": "Nằm giữa",
                            "empty": "Rỗng",
                            "equals": "Bằng với",
                            "not": "Không phải",
                            "notBetween": "Không nằm giữa",
                            "notEmpty": "Không rỗng"
                        },
                        "number": {
                            "between": "Nằm giữa",
                            "empty": "Rỗng",
                            "equals": "Bằng với",
                            "gt": "Lớn hơn",
                            "gte": "Lớn hơn hoặc bằng",
                            "lt": "Nhỏ hơn",
                            "lte": "Nhỏ hơn hoặc bằng",
                            "not": "Không phải",
                            "notBetween": "Không nằm giữa",
                            "notEmpty": "Không rỗng"
                        },
                        "string": {
                            "contains": "Chứa",
                            "empty": "Rỗng",
                            "endsWith": "Kết thúc bằng",
                            "equals": "Bằng",
                            "not": "Không phải",
                            "notEmpty": "Không rỗng",
                            "startsWith": "Bắt đầu với"
                        }
                    },
                    "logicAnd": "Và",
                    "logicOr": "Hoặc",
                    "add": "Thêm điều kiện",
                    "data": "Dữ liệu",
                    "deleteTitle": "Xóa quy tắc lọc"
                },
                "searchPanes": {
                    "countFiltered": "{shown} ({total})",
                    "emptyPanes": "Không có phần tìm kiếm",
                    "clearMessage": "Xóa hết",
                    "loadMessage": "Đang load phần tìm kiếm",
                    "collapse": {
                        "0": "Phần tìm kiếm",
                        "_": "Phần tìm kiếm (%d)"
                    },
                    "title": "Bộ lọc đang hoạt động - %d",
                    "count": "{total}"
                },
                "datetime": {
                    "hours": "Giờ",
                    "minutes": "Phút",
                    "next": "Sau",
                    "previous": "Trước",
                    "seconds": "Giây"
                },
                "emptyTable": "Không có dữ liệu",
                "info": "Hiển thị _START_ tới _END_ của _TOTAL_ dữ liệu",
                "infoEmpty": "Hiển thị 0 tới 0 của 0 dữ liệu",
                "lengthMenu": "Hiển thị _MENU_ dữ liệu",
                "loadingRecords": "Đang tải...",
                "paginate": {
                    "first": "Đầu tiên",
                    "last": "Cuối cùng",
                    "next": "Sau",
                    "previous": "Trước"
                },
                "search": "Tìm kiếm:",
                "zeroRecords": "Không tìm thấy kết quả"
            }
        })
        $(".delete-button").on("click", function (){
            App.showDeleteConfirmDialog().then((result) => {
                if (result.isConfirmed){
                    let id = $(this).attr("value");
                    deleteConfirm(id);
                }
            });
        })
        $(".edit-button").on("click",function (){
            let id = $(this).attr("value");
            editMember(id);
        })
    })
}

function editMember(memberID){
    $.ajax({
        type: "GET",
        url: `/members/edit-member/${memberID}`,
    }).done(function (member){
        $('#upID').val(member.member_id);
        $('#member_name').val(member.member_name);
        $('#phoneNumber').val(member.phoneNumber);
        $('#email').val(member.email);
        $('#classes').val(member.aclass.class_id);
        $('#editModal').modal('show');
    }).fail(function (){
        App.showErrorAlert("Đã xảy ra lỗi!")
    })
}

function saveMember(){
    let member_id = $("#upID").val();
    console.log(member_id)
    let member_name = $("#member_name").val();
    let phoneNumber = $("#phoneNumber").val();
    let email = $("#email").val();
    let classes = $("#classes").val();

    let newClasses = {
        class_id : classes
    }

    let member = {
        member_id: member_id,
        member_name: member_name,
        phoneNumber: phoneNumber,
        email: email,
        aclass: newClasses
    }

    console.log(member)
    if ($("#edit-member").valid()){
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PUT",
            data: JSON.stringify(member),
            url: `/members/edit/${member_id}`,
            success: function (members) {
                console.log(members)
                $('#row'+member_id+ ' td').remove();
                $('#row'+member_id).html(`
                        <td>${members.member_name}</td>
                        <td>${members.phoneNumber}</td>
                        <td>${members.email}</td>
                        <td>${members.aclass.class_name}</td>
                        <td class="text-center">
                            <button value="${members.member_id}" class="btn btn-outline-primary mr-2 edit-button" ><i class="fas fa-edit"></i>Sửa</button>
                            <button value="${members.member_id}" class="btn btn-outline-danger delete-button" ><i class="fas fa-trash-alt"></i>Xóa</button>
                        </td>`);
                $(".delete-button").on("click", function (){
                    App.showDeleteConfirmDialog().then((result) => {
                        if (result.isConfirmed){
                            let id = $(this).attr("value");
                            deleteConfirm(id);
                        }
                    });
                })
                $(".edit-button").on("click",function (){
                    let id = $(this).attr("value");
                    editMember(id);
                })
                $('#editModal').modal("hide");
                App.showSuccessAlert("Đã cập nhật thành công!")
                $("#edit-member")[0].reset();
            }
        })
    }
}

function deleteConfirm(memberID) {
    $.ajax({
        type : "DELETE",
        url : `/members/${memberID}`
    }).done(function (){
        $("#row" + memberID).remove();
        App.showSuccessAlert("Đã xóa thành công!")
    }).fail(function (){
        App.showErrorAlert("Đã xảy ra lỗi!")
    })
}


$(".edit-button").on("click",editMember);

$(".save-member").on("click",saveMember);

$(".delete-button").on("click",deleteConfirm);

getAllClass()

getAllMember();

$(() => {
    $("#edit-product").validate({
        errorElement: 'div',
        rules: {
            upProductName:  {
                required: true,
                minlength: 2,
                maxlength: 50,
            },
            upPrice: {
                required: true,
                number: true
            },
            upCountry:{
                required:true
            },
            upDescription:{
                required:true
            }
        },

        messages: {
            upProductName: {
                required: "Vui lòng nhập tên sản phẩm",
                minlength: "Vui lòng nhập tối thiểu 2 ký tự!",
                maxlength: "Vui lòng nhập tối đa chỉ có 50 ký tự!"
            },
            upPrice: {
                required: "Vui lòng nhập giá sản phẩm!",
                number: "Vui lòng chỉ nhập số"
            },
            upCountry: "Vui lòng chọn loại sản phẩm",
            upDescription:{
                required:"Vui lòng nhập mô tả!"
            }
        },

        submitHandler : saveMember
    });
});
