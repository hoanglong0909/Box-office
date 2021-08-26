App.getUser();
function getAllCategory(){
        $.ajax({
            type: "GET",
            url: "/categories/allCategory"
        }).done(function (category){
            let content = "";
            for (let i = category.length-1; i >= 0; i--) {
                content += `
                        <tr id="row${category[i].category_id}">
                          <td class="text-center">${category[i].category_name}</td>
                          <td class="text-center">
                            <button value="${category[i].category_id}" class="btn btn-outline-primary mr-2 edit-button" ><i class="far fa-edit"></i>Sửa</button>
                            <button value="${category[i].category_id}" class="btn btn-outline-danger delete-button" ><i class="fas fa-trash-alt"></i>Xóa</button>
                          </td>
                        </tr>
                `;
            }
            $("#categoryList tbody").html(content);
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
                editCategory(id);
            })
        })
}

function createCategory(){
    let category_name = $("#category_name").val();

    let category = {
        category_name:category_name
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(category),
        url: "/categories",
        success: function (category) {
            $('#categoryList tbody').append(`<tr id="row${category.category_id}">
                        <td class="text-center">${category.category_name}</td>
                        <td class="text-center">
                            <button value="${category.category_id}" class="btn btn-outline-primary mr-2 edit-button" ><i class="far fa-edit"></i>Edit</button>
                            <button value="${category.category_id}" class="btn btn-outline-danger delete-button" ><i class="fas fa-trash-alt"></i>Delete</button>
                        </td>
                    </tr>`);
            $("#create-form")[0].reset();
            $(".edit-button").on("click",editCategory);
            $(".save-category").on("click",saveCategory);
            $(".delete-button").on("click",deleteConfirm);

            App.showSuccessAlert("Create new product successfully!");
        }
    })
}

function editCategory(categoryID){
    $.ajax({
        type: "GET",
        url: `/categories/edit-category/${categoryID}`,
        success:function (category){
            $('#upID').val(category.category_id);
            $('#upCategoryName').val(category.category_name);
            $('#editModal').modal('show');
        }
    })
}

function saveCategory(){
    let category_id = $("#upID").val();
    let category_name = $("#upCategoryName").val();

    let category = {
        category_id:category_id,
        category_name: category_name
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(category),
        url: `/categories/edit/${category_id}`,
        success: function (category) {
            $('#row'+category_id+ ' td').remove();
            $('#row'+category_id).html(`
                        <td class="text-center">${category.category_name}</td>
                        <td class="text-center">
                            <button value="${category.category_id}" class="btn btn-outline-primary mr-2 edit-button" ><i class="fas fa-edit"></i>Edit</button>
                            <button value="${category.category_id}" class="btn btn-outline-danger delete-button" ><i class="fas fa-trash-alt"></i>Delete</button>
                        </td>`);
            $(".edit-button").on("click",editCategory);
            $(".delete-button").on("click",deleteConfirm);
            $('#editModal').modal("hide");
            App.showSuccessAlert("Edit product successfully!")
            $("#edit-product")[0].reset();
        }
    })
}

function deleteConfirm(categoryID) {
        App.showDeleteConfirmDialog().
        then((result) => {
        if (result.isConfirmed) {
            //Gọi ajax
            $.ajax({
                type: "DELETE",
                //tên API
                url: `/categories/${categoryID}`,
                //xử lý khi thành công
                success: function () {

                    // $(`#row${data.id}`).html("");
                    $("#row" + categoryID).remove();

                    App.showSuccessAlert("Xóa thành công!")
                }
            })
        }
    })
}



$("#create-button").on("click",createCategory);
$(".edit-button").on("click",editCategory);
$(".save-category").on("click",saveCategory);
$(".delete-button").on("click",deleteConfirm);

getAllCategory();