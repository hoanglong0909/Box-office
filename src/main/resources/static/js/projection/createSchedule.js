App.getUser();

function createSchedule(){
    let schedule_date = $("#schedule_date").val();
    let shows = [];

    let schedule = {
        schedule_date : schedule_date,
        shows: shows
    }

    // if ($("#create-form").valid()){
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(schedule),
            url: "/schedules/create-schedule"
        }).done(function (){
            $("#create-form")[0].reset();
            App.showSuccessAlert("Tạo mới lịch chiếu thành công");
        }).fail(()=>{
            App.showErrorAlert("Đã xảy ra lỗi. Lịch chiếu đã tồn tại hoặc đã quá hạn! Vui lòng kiểm tra lại!");
        })
    // }
}

$("#create-button").on("click",createSchedule)