App.getUser();
function getAllCategory(){
    $.ajax({
        type: "GET",
        url: "/products/allCategory"
    }).done(function (category){
        let content = "";
        for (let i = 0; i < category.length; i++) {
            content += `
                    <option value="${category[i].category_id}">${category[i].category_name}</option>
                `;
        }
        $("#category").html(content);
    })
}

function createProduct(){
    let product_name = $("#product_name").val();
    let price = $("#price").val();
    let description = $("#description").val();
    let category = $("#category").val();

    let newCategory = {
        category_id : category
    }

    let product = {
        product_name : product_name,
        price : price,
        description : description,
        category : newCategory
    }

    if ($("#create-form").valid()){
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(product),
            url: "/products/createNewProduct"
        }).done(function (){
            $("#create-form")[0].reset();
            App.showSuccessAlert("Tạo mới sản phẩm thành công");
        }).fail(()=>{
            App.showErrorAlert("Đã xảy ra lỗi!");
        })
    }
}

$("#create-button").on("click",createProduct);

getAllCategory();

$(() => {
    $("#create-form").validate({
        errorElement: 'div',
        rules: {
            product_name:  {
                required: true,
                minlength: 5,
                maxlength: 50,
            },
            price: {
                required: true,
                number: true
            },
            upCountry:{
                required:true
            },
            description:{
                required:true
            }
        },

        messages: {
            product_name: {
                required: "Vui lòng nhập tên sản phẩm",
                minlength: "Vui lòng nhập tối thiểu 2 ký tự!",
                maxlength: "Vui lòng nhập tối đa chỉ có 50 ký tự!"
            },
            price: {
                required: "Vui lòng nhập giá sản phẩm!",
                number: "Vui lòng chỉ nhập số"
            },
            upCountry: "Vui lòng chọn loại sản phẩm",
            description:{
                required:"Vui lòng nhập mô tả!"
            }
        },

        submitHandler : createProduct
    });
});

