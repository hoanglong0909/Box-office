App.getUser();


function getSchedules() {
    $.ajax({
        type: "GET",
        url: "/schedules/allSchedule"
    }).done(function (schedules){
        console.log(schedules)
        let content = "";
        for (let i = 0; i < schedules.length; i++) {
            content += `
                <option value="${schedules[i].schedule_id}" selected>${schedules[i].schedule_date}</option>
            `;
        }
        $("#schedule_date").html(content);
        $("#schedule_date").on("change",function (){
            getAllFilm()
        });
    })
}
getSchedules();

function checkAvailable() {
    $.ajax({
        type: "GET",
        url: "/films/checkAvailable"
    }).done(getAllFilm)
}

function getAllFilm() {
    let schedule_date = $("#schedule_date option:selected").text();
    $.ajax({
        type: "GET",
        url: "/films/allTrueFilm"
    }).done(function (films){
        let content = "";
        for (let i = films.length-1; i >= 0; i--) {
            if(films[i].exp_date > schedule_date){
                content += `
                    <option value="${films[i].film_id}"> ${films[i].film_name}</option>
                `;
            }
        }
        $("#film_id").html(content);
    })
}

checkAvailable();

//Tạo suất chiếu

$("#create-button").on("click", createShow);


function createShow() {
    let film_id = $("#film_id").val();
    $.ajax({
        type: "GET",
        url: `/films/find/${film_id}`
    }).done((film) => {
        let room = {
            room_name: $("#room_name").val()
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: "/room/create",
            data: JSON.stringify(room)
        }).done((room1) => {
            $.ajax({
                type: "POST",
                url: `/room/initSeat/${room1.room_id}`
            }).done(() => {
                let schedule = {
                    schedule_id: $("#schedule_date").val()
                }
                let bonus_time = "00:30:00";
                let time_start = $("#time_start").val();
                let time_end = App.addTimes(App.addTimes(time_start, film.duration), bonus_time);
                console.log(time_end)
                let show = {
                    schedule: schedule,
                    film: film,
                    room: room1,
                    time_start: time_start,
                    time_end: time_end
                }
                if ($("#create-form").valid()) {
                    $.ajax({
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        type: "POST",
                        url: "/show/create",
                        data: JSON.stringify(show)
                    }).done((newShow) =>{
                        let newRoom = {
                            room_id: room1.room_id,
                            isFull: room1.isFull,
                            room_name: room1.room_name,
                            show: newShow
                        }

                        $.ajax({
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            type: "PUT",
                            url: "/room/update",
                            data: JSON.stringify(newRoom)
                        })
                        $("#create-form")[0].reset();
                        App.showSuccessAlert("Tạo mới suất chiếu thành công!")
                    }).fail(() => {
                        App.showErrorAlert("Xãy ra lỗi. Hoặc suất chiếu đã tồn tại!")
                    })
                }
            })
        })
    })
}



$(() => {
    $("#create-form").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            time_start: {
                required: true,
                validateTime: true,
            },
        },
        messages: {
            time_start: {
                required: "Hãy điền giờ bắt đầu",
            },
        },
        submitHandler: createShow
    });
})
$.validator.addMethod("validateTime", function (value, element) {
    if (!/^\d{2}:\d{2}:\d{2}$/.test(value)) return false;
    var parts = value.split(':');
    if (parts[0] > 23 || parts[1] > 59 || parts[2] > 59) return false;
    return true;
}, "Hãy nhập theo đúng định dạng");