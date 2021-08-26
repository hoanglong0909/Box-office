App.getUser();

//Tạo tài khoản

function user(){

    let user = {
        username: $("#username").val(),
        password: $("#password").val(),
        phone: $("#phone").val(),
        email: $("#email").val(),
        fullName: $("#fullName").val(),
        role: {
            id: $("#role").val()
        }
    }

    if ($("#create-form").valid()){
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(user),
            url: "/user/create"
        }).done(() => {
            $("#create-form")[0].reset();
            App.showSuccessAlert("Đăng ký thành công!");
        }).fail(()=>{
            App.showErrorAlert("Có lỗi đã xảy ra, vui lòng kiểm tra lại!")
        })
    }
}

function getAllRole(){
    $.ajax({
        type: "GET",
        url: "/role/allRole"
    }).done((roles) => {
        let content = "";
        for (let i = 0; i < roles.length; i++) {
            content += `
                <option value="${roles[i].id}">${roles[i].name}</option>
            `;
        }
        $("#role").html(content);
    })
}

getAllRole();

$("#create-button").on("click",user);

$.validator.addMethod("validatePhone", function (value, element) {
    return this.optional(element) || /((09|03|07|08|05)+([0-9]{8})\b)/g.test(value);
}, "Hãy nhập số điện thoại hợp lệ!!!");

$.validator.addMethod("validateEmail", function (value, element){
    return this.optional(element) ||  /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
}, "Hãy nhập email hợp lệ!!!")

$(() => {
    $("#create-form").validate({
        errorElement: 'div',
        rules: {
            username:  {
                required: true,
                minlength: 4,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 4,
                maxlength: 20
            },
            phone: {
                required: true,
                minlength: 10,
                maxlength: 11,
                validatePhone: true
            },
            email:{
                required: true,
                validateEmail: true
            },
            fullName:{
                required: true
            },
            role: {
                required: true
            }
        },
        messages: {
            username: {
                required: "Vui lòng nhập username",
                minlength: "Vui lòng nhập tối thiểu 4 ký tự!",
                maxlength: "Vui lòng nhập tối đa chỉ có 20 ký tự!"
            },
            password: {
                required: "Vui lòng nhập mật khẩu!",
                minlength: "Vui lòng nhập tối thiểu 4 ký tự!",
                maxlength: "Vui lòng nhập tối đa chỉ có 20 ký tự!"
            },
            phone: {
                required: "Vui lòng nhập số điện thoại!",
                minlength: "Vui lòng nhập tối thiểu 10 số!",
                maxlength: "Vui lòng nhập tối đa 11 số!"
            },
            email: {
                required: "Vui lòng nhập email!"
            },
            fullName: {
                required: "Vui lòng họ tên!"
            },
            role: {
                required: "Vui lòng chọn chức vụ"
            }
        },
        submitHandler : user
    });
});

//Hiển thị danh sách tài khoản

function getListUsers(){
    $.ajax({
        type: "GET",
        url: "/user/listUsers"
    }).done((users) =>{
        let content = "";
        for (let i = users.length - 1; i >=0 ; i--) {
            content += `
                <tr id="row${users[i].id}">
                    <td class="text-center">${users[i].fullName}</td>
                    <td class="text-center">${users[i].username}</td>
                    <td class="text-center">${users[i].email}</td>
                    <td class="text-center">${users[i].phone}</td>
                    <td class="text-center">${users[i].role.name}</td>
                    <td>
                       <button value="${users[i].id}" class="btn btn-outline-primary mr-2 edit-button" ><i class="far fa-edit"></i>Sửa</button>
                       <button value="${users[i].id}" class="btn btn-outline-danger delete-button" ><i class="fas fa-trash-alt"></i>Xóa</button>
                    </td>
                </tr>   
            `;
        }
        $("#listUsers").html(content);
        $("#inline-editable").DataTable({
            columnDefs: [
                { orderable: false, targets: [5] },
                { searchable: false, targets: [1,5] }
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
                if (result.isConfirmed) {
                    let id = $(this).attr("value");
                    deleteUser(id);
                    App.showSuccessAlert("Xóa thành viên thành công!")
                }
            })
        })
        $(".edit-button").on("click", function (){
            let id = $(this).attr("value");
            $.ajax({
                type: "GET",
                url: `/user/getUser/${id}`
            }).done((user)=> {
                $("#userId").val(user.id);
                $("#edit-username").val(user.username);
                $("#edit-phone").val(user.phone);
                $("#edit-email").val(user.email);
                $("#edit-fullName").val(user.fullName);
                $("#role").val(user.role.id);
                $("#editModal").modal("show");
            })
        })
    })
}

function deleteUser(id){
    $.ajax({
        type: "POST",
        url: `/user/delete/${id}`
    }).done((user) => {
        $(`#row${user.id}`).html("");
        App.showSuccessAlert("Xóa tài khoản thành công!");
    }).fail(() => {
        App.showErrorAlert("Có lỗi xảy ra!");
    })
}

function saveUser(){
    let userId = $("#userId").val();

    let newUser = {
        username: $("#edit-username").val(),
        phone: $("#edit-phone").val(),
        email: $("#edit-email").val(),
        fullName: $("#edit-fullName").val(),
        role: {
            id: $("#role").val()
        }
    }


    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: `/user/edit/${userId}`,
        data: JSON.stringify(newUser)
    }).done((user) =>{
        let content = `
    <td class="text-center">${user.fullName}</td>
                <td class="text-center">${user.username}</td>
                <td class="text-center">${user.email}</td>
                <td class="text-center">${user.phone}</td>
                <td class="text-center">${user.role.name}</td>
                <td>
                   <button value="${userId}" class="btn btn-outline-primary mr-2 edit-button" ><i class="far fa-edit"></i>Sửa</button>
                        <button value="${userId}" class="btn btn-outline-danger delete-button" ><i class="fas fa-trash-alt"></i>Xóa</button>
                </td>
    `;
        $("#row" + userId).html(content);

        $(".delete-button").on("click", function (){
            App.showDeleteConfirmDialog().then((result) => {
                if (result.isConfirmed) {
                    let id = $(this).attr("value");
                    deleteUser(id);
                    App.showSuccessAlert("Xóa thành viên thành công!")
                }
            })
        })

        $(".edit-button").on("click", function (){
            let id = $(this).attr("value");
            $.ajax({
                type: "GET",
                url: `/user/getUser/${id}`
            }).done((user)=> {
                $("#userId").val(user.id);
                $("#edit-username").val(user.username);
                $("#edit-phone").val(user.phone);
                $("#edit-email").val(user.email);
                $("#edit-fullName").val(user.fullName);
                $("#role").val(user.role.id);
                $("#editModal").modal("show");
            })
        })
    })


}

$(".save-user").on("click", function (){
    if ($("#edit-form").valid()){
        saveUser();
        $("#editModal").modal("hide");
        $("#edit-form")[0].reset();
        App.showSuccessAlert("Cập nhật thông tin tài khoản thành công!");
    }

});

getListUsers();

//Danh sách tai khoản bị xóa

function getDeletedUsers(){
    $.ajax({
        type: "GET",
        url: "/user/getDeletedUser"
    }).done((users) =>{
        let content = "";
        for (let i = users.length - 1; i >=0 ; i--) {
            content += `
                <tr id="row${users[i].id}">
                    <td class="text-center">${users[i].fullName}</td>
                    <td class="text-center">${users[i].username}</td>
                    <td class="text-center">${users[i].email}</td>
                    <td class="text-center">${users[i].phone}</td>
                    <td class="text-center">${users[i].role.name}</td>
                    <td>
                       <button value="${users[i].id}" class="btn btn-outline-primary mr-2 restore-button" ><i class="far fa-window-restore"></i> Khôi phục</button> 
                       <button value="${users[i].id}" class="btn btn-outline-danger delete-btn" ><i class="fas fa-trash-alt"></i>Xóa</button> 
                    </td>
                </tr>   
            `;
        }
        $("#deletedUsers").html(content);
        $(".delete-btn").on("click", function (){
            App.showDeleteConfirmDialog().then((result) => {
                if (result.isConfirmed) {
                    let id = $(this).attr("value");
                    removeUser(id);
                    App.showSuccessAlert("Đã xóa thành viên thành công!")
                }
            })
        })
        $(".restore-button").on("click", function (){
            App.showRestoreConfirmDialog().then((result) => {
                if (result.isConfirmed) {
                    let id = $(this).attr("value");
                    deleteUser(id);
                    App.showSuccessAlert("Khôi phục thành viên thành công!")
                }
            })
        })
    })
}

function removeUser(id){
    $.ajax({
        type : "DELETE",
        url : `/user/${id}`
    }).done(function (){
        $("#row" + id).remove();
        App.showSuccessAlert("Đã xóa thành viên thành công!")
    }).fail(function (){
        App.showErrorAlert("Đã xảy ra lỗi!")
    })
}

$(() => {
    $("#edit-form").validate({
        errorElement: 'div',
        rules: {
            username:  {
                required: true,
                minlength: 4,
                maxlength: 20
            },
            phone: {
                required: true,
                minlength: 10,
                maxlength: 11,
                validatePhone: true
            },
            email:{
                required: true,
                validateEmail: true
            },
            fullName:{
                required: true
            },
            role: {
                required: true
            }
        },
        messages: {
            username: {
                required: "Vui lòng nhập username",
                minlength: "Vui lòng nhập tối thiểu 4 ký tự!",
                maxlength: "Vui lòng nhập tối đa chỉ có 20 ký tự!"
            },
            phone: {
                required: "Vui lòng nhập số điện thoại!",
                minlength: "Vui lòng nhập tối thiểu 10 số!",
                maxlength: "Vui lòng nhập tối đa 11 số!"
            },
            email: {
                required: "Vui lòng nhập email!"
            },
            fullName: {
                required: "Vui lòng họ tên!"
            },
            role: {
                required: "Vui lòng chọn chức vụ"
            }
        },
        submitHandler : saveUser
    });
});

getDeletedUsers();