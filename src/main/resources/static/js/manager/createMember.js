App.getUser();

function createMember(){
    let member_name = $("#member_name").val();
    let phoneNumber = $("#phoneNumber").val();
    let email = $("#email").val();


    let member = {
        member_name: member_name,
        phoneNumber: phoneNumber,
        email : email
    }

    if ($("#create-form").valid()){
        console.log(member)
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(member),
            url: "/members/createNewMember"
        }).done(function (e){
            console.log(e)
            $("#create-form")[0].reset();
            App.showSuccessAlert("Tạo mới thành viên thành công");
        }).fail(()=>{
            App.showErrorAlert("Đã xảy ra lỗi!");
        })
    }
}

$("#create-button").on("click",createMember);


$(() => {
    $("#create-form").validate({
        errorElement: 'div',
        rules: {
            member_name:  {
                required: true,
                minlength: 5,
                maxlength: 50,
            },
            phoneNumber: {
                required: true,
                number: true,
                minlength:10,
                maxlength:11,
                validatePhone:true
            },
            email:{
                required:true
            }
        },

        messages: {
            member_name: {
                required: "Vui lòng nhập họ và tên",
                minlength: "Vui lòng nhập tối thiểu 2 ký tự!",
                maxlength: "Vui lòng nhập tối đa chỉ có 50 ký tự!"
            },
            phoneNumber: {
                required: "Vui lòng nhập số điện thoại!",
                number: "Vui lòng chỉ nhập số",
                minlength: "Vui lòng nhập tối thiểu 10 số!",
                maxlength: "Vui lòng nhập tối đa chỉ có 11 số!"
            },
            email:{
                required:"Vui lòng nhập địa chỉ email"
            }
        },

        submitHandler : createMember
    });
});
$.validator.addMethod("validatePhone", function (value, element) {
        return this.optional(element) || /((09|03|07|08|05)+([0-9]{8})\b)/g.test(value);
    }, "Hãy nhập số điện thoại hợp lệ!!!");