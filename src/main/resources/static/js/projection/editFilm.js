App.getUser();

function editFilm(){
    let arr = $(location).attr('href').split("/");
    let film_id = arr[arr.length -1];
    $.ajax({
        type: "GET",
        url: `/films/edit-form/${film_id}`,
    }).done(function (){
        $.ajax({
            type: "GET",
            url: `/films/edit-film/${film_id}`,}).done(function (film){
            let content ='';
            content += `<div class="form-row">
                  <div class="form-group col-md-6">
                    <input type="hidden" id="up_id" value="${film.film_id}">
                    <input type="hidden" id="up_admit" value="${film.admit}">
                    <input type="hidden" id="up_status">
                    <label>Tên Phim<span class="text-danger">*</span></label>
                    <input type="text" name="up_name" parsley-trigger="change"  placeholder="Nhập tên phim" class="form-control" id="up_name" value="${film.film_name}">
                  </div>
                  <div class="form-group col-md-6">
                    <label>Thời lượng*(hh:mm:ss)<span class="text-danger">*</span></label>
                    <input id="up_duration" name="up_duration"  type="String" placeholder="Nhập thời lượng theo định dạng"  class="form-control" value="${film.duration}">
                  </div>
                  <div class="form-group col-md-6">
                    <label>Ngày công chiếu<span class="text-danger">*</span></label>
                    <input id="up_relDate" name="up_relDate" type="date" placeholder="Nhập ngày công chiếu"  class="form-control" value="${film.rel_date}">
                  </div>
                  <div class="form-group col-md-6">
                    <label>Ngày hết hạn<span class="text-danger">*</span></label>
                    <input id="up_expDate" name="up_expDate" type="date" placeholder="Nhập ngày hết hạn"  class="form-control" value="${film.exp_date}">
                  </div>
                  <div class="form-group col-md-12">
                    <label>Mô tả</label>
                    <textarea name="up_description" class="form-control" id="up_description" rows="3">${film.description}</textarea>
                  </div>
                 <div class="form-group col-md-12">
                    <form id="singleUploadForm" name="singleUploadForm">
                        <label>
                            Chọn ảnh:
                            <input id="singleFileUploadInput" type="file" name="file" class="file-input">
                        </label>
                        <input type="submit" class="upload" hidden>
                    </form>
                    <input type="text" hidden id="imageName" value="">
                    <img disabled style="object-fit: cover"  width="100px" height="100px" src= "/uploads/${film.image}"  alt="${film.film_name}">
                </div>
                <div class="form-group text-right col-md-12">
                  <button class="btn btn-primary waves-effect waves-light mr-1" id="save-film" type="button">
                   Cập nhật
                  </button>
                  <button  class="btn btn-secondary waves-effect waves-light">
                    <a class="text-white exit-button" href="/films/listFilm">Thoát</a>
                  </button>
                </div>`;
            $("#edit-film").html(content);
            $("#save-film").on("click",saveFilm);
        })
    }).fail(function (){
        App.showErrorAlert("Đã xảy ra lỗi!")
    })
}

editFilm();

function saveFilm(){
    let files = singleFileUploadInput.files;
    $("#imageName").val(files[0].name);
    uploadEditSingleFile(files[0]);

    let film_id = $("#up_id").val();
    let film_name = $("#up_name").val();
    let duration = $("#up_duration").val();
    let image = $("#imageName").val();
    let exp_date = $("#up_expDate").val();
    let rel_date = $("#up_relDate").val();
    let description = $("#up_description").val();
    let admit = $("#up_admit").val();


    let newFilm = {
        film_id:film_id,
        film_name : film_name,
        duration : duration,
        image : image,
        exp_date : exp_date,
        rel_date : rel_date,
        description : description,
        admit:admit
    }

    console.log(newFilm)
    if ($("#edit-film").valid()){
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PUT",
            data: JSON.stringify(newFilm),
            url: `/films/edit-film/${film_id}`
        }).done(function (){
            $("#edit-film")[0].reset();
            App.showSuccessAlert("Đã cập nhật thành công!")
            window.location.href = "/films/listFilm";
        })
    }
}

function getFilm(){
    $.ajax({
        type: "GET",
        url: "/films/allFilm"
    }).done(function (films){
        let content = "";
        for (let i = films.length-1; i >= 0; i--) {
            content += `
                            <div class="col-md-6" >
                                <div class="ml-2 mr-2 row g-0 border rounded overflow-hidden flex-md-row  mb-4 shadow-sm h-md-300  position-relative ">
                                    <div class="col-auto d-none d-lg-block">
                                     <img style="object-fit: cover"  width="220" height="310" src= "/uploads/${films[i].image}"  alt="${films[i].film_name}">
                                    </div>
                                    <div class="col p-4 d-flex flex-column position-static">
                                        <div>
                                        <h4 class="mb-5"><b>${films[i].film_name}</b></h4>
                                        </div>
                                        <div class="mb-2"><strong>Thời lượng:</strong></br> ${films[i].duration}</div>
                                        <p class=" mb-2">
                                           <strong>Ngày công chiếu:</strong></br> ${films[i].exp_date}
                                        </p>
                                          <p class="mb-2">
                                           <strong>Lượt xem: ${films[i].admit}</strong></br>
                                        </p>
                                        <a href="/films/edit-form/${films[i].film_id}" value="${films[i].film_id}" type="submit" class="edit-button">
                                        <i class="edit-button" style="color: #0a53be">Chi tiết</i></a>
                                        </a> 
                                    </div>
                                    <div>
                                        <button style="width: 120px; height: 40px; color: #3c8dbc!important;float: right" class="btn ${films[i].status ? "btn-secondary" : "btn-success"}" 
                                        value="${films[i].status ? "Chưa công chiếu" : "Đang công chiếu"}">
                                        </button>
                                    </div>
                                </div>
                            </div>
                `;
        }
        $("#listFilm tbody").html(content);
    })
}

$("#save-film").on("click",saveFilm)


function uploadEditSingleFile(file) {
    let formData = new FormData();
    formData.append("file", file);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadFile");
    xhr.send(formData);
}

$(() => {
    $("#edit-film").validate({
        errorElement: 'div',
        rules: {
            up_name:  {
                required: true,
                maxlength: 100,
            },
            up_duration: {
                required: true,
            },
            up_relDate: {
                required:true
            },
            up_expDate: {
                required:true
            },
            up_description:{
                required:true
            }
        },

        messages: {
            up_name: {
                required: "Vui lòng nhập tên phim!",
                maxlength: "Vui lòng nhập tối đa chỉ có 100 ký tự!"
            },
            up_duration: {
                required: "Vui lòng nhập thời lượng phim đúng định dạng!",
            },
            up_relDate:{
                required:"Vui lòng chọn ngày công chiếu!"
            },
            up_expDate:{
                required:"Vui lòng chọn ngày kết thúc chiếu!"
            },
            up_description: {
                required:"Vui lòng nhập mô tả của phim!"
            }
        },
        submitHandler : saveFilm
    });
});