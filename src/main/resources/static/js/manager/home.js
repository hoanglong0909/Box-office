App.getUser();

function sumAdmit(){
    let month = App.getCurrentMonth();
    console.log(month)
    $.ajax({
        type: "GET",
        url: `/orders/monthlyAdmit/${month}`
    }).done(function (orders){
        let content = "";
        let admit = 0;
        for (let i = 0; i < orders.length ; i++) {
            admit += orders[i].admit;
        }
        content += `<h2 class="my-0"><span data-plugin="counterup">${admit} vé</span></h2>
                    <p class="fw-bold mb-0">Lượt khách tháng ${month}</p>`;
        $("#sum_admit").html(content);
    })
}
sumAdmit();

function totalPrice(){
    let month = App.getCurrentMonth();
    $.ajax({
        type:"GET",
        url: `/orders/totalMonth/${month}`
    }).done(function (orders){
        let content = "";
        // let total_price = 0;
        // for (let i = 0; i < orders.length; i++) {
        //     total_price += orders.total_price
        // }
        content += `<h2 class="my-0"><span data-plugin="counterup">${orders[0].total_price.toLocaleString('vi', {style : 'currency', currency : 'VND'})}</span></h2>
                    <p class="fw-bold mb-0">Doanh thu tháng ${month}</p>`;
        $("#total_price").html(content);
    })
}
totalPrice();

function totalMember(){
    $.ajax({
        type:"GET",
        url: "/orders/totalMember"
    }).done(function (members){
        let total_member = members.length;
        let content = "";
        content += `<h2 class="my-0"><span data-plugin="counterup">${total_member}</span></h2>
                    <p class="fw-bold mb-0">Thành viên</p>`;
        $("#total_member").html(content);
    })
}
totalMember();