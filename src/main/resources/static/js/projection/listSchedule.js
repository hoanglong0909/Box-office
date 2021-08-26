App.getUser();

function getAllSchedule(schedule_month) {
    console.log(schedule_month)
    $.ajax({
        type: "GET",
        url: `/schedules/allScheduleByMonth/${schedule_month}`
    }).done(function (schedules) {
        let content = "";
        for (let i = 0; i < schedules.length; i++) {
            content += `
                <div class="col-lg-4">
                    <div value="${schedules[i].schedule_id}" class="btnSchedule btn our-team-main modalShows" style="background-color: rgb(225, 243, 245)" >
                        <div class="team-front">
                          <h3>Lịch chiếu ngày: </h3>
                          <h2>${schedules[i].schedule_date}</h2>
                        </div>
                    </div>
                </div>
                `;
        }
        $("#listSchedule tbody").html(content);
        $(".modalShows").on("click", function () {
            let id = $(this).attr("value");
            getShowList(id);
        })
    })
}
$("#schedule_month").on("change",function (){
    let schedule_month = $("#schedule_month").val();
        getAllSchedule(schedule_month);
})


function getScheduleMonth(){
    $("#schedule_month").val(App.getMonth().toString());
}
getScheduleMonth();



function getShowList(id) {
    $.ajax({
        type: "GET",
        url: `/show/allShowsToday/${id}`
    }).done(function (shows){
        console.log(shows)
        let content = "";
        for (let i = 0; i < shows.length; i++) {
            let room_id = shows[i].room.room_id;
            content += `
            <div class="col-md-4">
                   <div value="${room_id}" class="btnShow our-team-main modalShows" style="background-color: rgb(225, 243, 245)" >
                        <div class="scheduleShow">
                            <div class="col-lg-6"><h4 class="h4Schedule">${shows[i].schedule.schedule_date}</h4></div>
                                <div class="setActive ${shows[i].status ? "btn-success" : "btn-secondary"}"
                                    >${shows[i].status ? "Active" : "InActive"}
                                </div>
                                <div class="bodyShow">
                                     <div class="c">
                                        <a class="a">Phim:</a>
                                        <a class="b1">${shows[i].film.film_name}</a> 
                                     </div>
                                    <div class="c">
                                        <a class="a">Thời lượng: </a>
                                        <a class="b2">${shows[i].film.duration} </a>
                                    </div>
                                    
                                    <div class="c">
                                        <a class="a">Rạp: </a>
                                        <a class="b3">${shows[i].room.room_name} </a>
                                    </div>
                                    <div class="c">
                                        <a class="a">Giờ bắt đầu: </a>
                                         <a class="b4">${shows[i].time_start}</a>   
                                    </div>
                                    <div class="d">
                                       <span id="statusSeat${shows[i].room.room_id}" class="admit" value="${shows[i].room.room_id}"></span>
                                    </div> 
                                </div>     
                            </div>
                        </div>  
                   </div>
            </div>
        `;
        }
        for (let i = 0; i <shows.length ; i++) {
            $.ajax({
                type: "GET",
                url: `/seat/getSeatsByRoom/${shows[i].room.room_id}`
            }).done(function (seats) {
                $(`#statusSeat${shows[i].room.room_id}`).html(`
                <p>${App.countTakenSeat(seats)}/100</p>
        `)
            })
        }
        $("#show-modal fieldset").html(content);
        $("#editModal").modal('show');
    })
}

function searchSchedule(schedule_date) {
    if (schedule_date === "") {
        $.ajax({
            type: "GET",
            url: `/schedules/allSchedule`
        }).done(function (schedule) {
            let content = "";
            for (let i = 0; i < schedule.length; i++) {
                content += `<div class="col-lg-4">
                    <div value="${schedule[i].schedule_id}" class="btnSchedule btn our-team-main modalShows" style="background-color: rgb(225, 243, 245)" >
                        <div class="team-front">
                          <h3>Lịch chiếu ngày: </h3>
                          <h2>${schedule[i].schedule_date}</h2>
                        </div>
                    </div>
                </div>`;
            }
            $("#listSchedule tbody").html(content)
            $(".modalShows").on("click", function () {
                let id = $(this).attr("value");
                getShowList(id);
            })
        })
    } else {
        $.ajax({
            type: "GET",
            url: `/schedules/searchSchedule/${schedule_date}`,
        }).done(function (schedule) {
            console.log(schedule)
            let content = "";
            for (let i = 0; i < schedule.length; i++) {
                content += `<div class="col-lg-4">
                    <div value="${schedule[i].schedule_id}" class="btnSchedule btn our-team-main modalShows" style="background-color: rgb(225, 243, 245)" >
                        <div class="team-front">
                          <h3>Lịch chiếu ngày: </h3>
                          <h2>${schedule[i].schedule_date}</h2>
                        </div>
                    </div>
                </div>`;
            }
            $("#listSchedule tbody").html(content)
            $(".modalShows").on("click", function () {
                let id = $(this).attr("value");
                getShowList(id);
            })
        })
    }
}

$("#search").on("input", function () {
    let schedule_date = $("#search").val();
    searchSchedule(schedule_date);
})

getAllSchedule();