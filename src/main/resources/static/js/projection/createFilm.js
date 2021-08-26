App.getUser();

let singleUploadForm = document.querySelector('#singleUploadForm');
let singleFileUploadInput = document.querySelector('#singleFileUploadInput');

function uploadSingleFile(file) {
    let formData = new FormData();
    formData.append("file", file);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadFile");
    xhr.send(formData);
}

$("#singleUploadForm").on("submit",function (){
    let files = singleFileUploadInput.files;
    $("#imageName").val(files[0].name);
    uploadSingleFile(files[0]);
})

function createFilm(){
    let files = singleFileUploadInput.files;
    $("#imageName").val(files[0].name);
    uploadSingleFile(files[0]);

    let film_id = $('#film_id').val();
    let film_name = $("#film_name").val();
    let image = $("#imageName").val();
    let duration = $("#film_duration").val();
    let rel_date = $("#rel_date").val();
    let exp_date = $("#exp_date").val();
    let description = $("#film_description").val();
    let admit = 0;


    let newFilm = {
        film_id : film_id,
        film_name : film_name,
        image : image,
        duration : duration,
        rel_date : rel_date,
        exp_date : exp_date,
        description : description,
        admit : admit,
    }
    if ($("#create-form").valid()){
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(newFilm),
            url: "/films/createFilm"
        }).done(function (){
            $("#create-form")[0].reset();
            App.showSuccessAlert("Tạo mới phim thành công");
        }).fail(()=>{
            App.showErrorAlert("Đã xảy ra lỗi. Vui lòng kiểm tra lại!");
        })
    }
}
function updateDate(){
    let date = $("#rel_date").val();
    let release_date = date.split("-");
    $("#exp_date").val(release_date[0]+"-"+release_date[1]+"-"+release_date[2]);
}
$('#rel_date').on("change",updateDate);



$(() => {
    $("#create-form").validate({
        errorElement: 'div',
        rules: {
            film_name:  {
                required: true,
                maxlength: 100,
            },
            film_duration: {
                required: true,
                validTime:true
            },
            rel_date: {
                required:true
            },
            exp_date: {
                required:true
            },
            film_description:{
                required:true,
            },
        },

        messages: {
            film_name: {
                required: "Vui lòng nhập tên phim!",
                maxlength: "Vui lòng nhập tối đa chỉ có 100 ký tự!"
            },
            film_duration: {
                required: "Vui lòng nhập thời lượng phim !",
            },
            rel_date:{
                required:"Vui lòng chọn ngày công chiếu!"
            },
            exp_date:{
                required:"Vui lòng chọn ngày kết thúc chiếu!"
            },
            film_description: {
                required:"Vui lòng nhập mô tả của phim!",
            }
        },
        submitHandler : createFilm
    });
});

$("#create-button").on("click",createFilm);

$.validator.addMethod("validTime", function(value, element) {
    if (!/([01]?[0-9]|2[0-3]):[0-5][0-9]/.test(value)) return false;
    let parts = value.split(':');
    return !(parts[0] > 23 || parts[1] > 59 || parts[2] > 59);
}, "Hãy nhập thời gian hợp lệ !");
