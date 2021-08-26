App.getUser();

function getListOrderDate(){
    let order_date  = $('#order_date').val();
    $.ajax({
        type : "GET",
        url : `/orders/findByOrderDate/${order_date}`
    }).done(function (orders){
        let content = "";
        if(orders.length === 0){
            content = "Chưa Thực hiện giao dịch"
        }else {
            for (let i = 0; i < orders.length; i++) {
                content += `
             <tr id="row${orders[i].order_id}">
                              <input hidden id="${orders[i].order_id}">
                              <td>${orders[i].order_id}</td>
                              <td>${orders[i].order_time}</td>
                              <td class="text-right"><a value="${orders[i].order_id}" class="btn modalProduct">${orders[i].total_product.toLocaleString('vi', {
                    style: 'currency',
                    currency: 'VND'
                })}</a></td>
                              <td class="text-right"><a value="${orders[i].order_id}" class="btn modalTicket">${orders[i].total_ticket.toLocaleString('vi', {style: 'currency', currency: 'VND'})}</a></td>
                              <td class="text-right">${orders[i].total_price.toLocaleString('vi', {style: 'currency', currency: 'VND'})}</td>
                              <td>${orders[i].member == null ? "None" : orders[i].member.member_name}</td>
                              <td>${orders[i].user.username}</td>
                        </tr>
                        
            `;
            }
        }
        $("#orderList tbody").html(content);
        $('.modalProduct').on("click", function () {
            let id = $(this).attr('value');
            getTotalProduct(id);
        })
        $('.modalTicket').on('click',function (){
            let id = $(this).attr('value');
            getTotalTicket(id);
        })

    })
}

function getTotalAllPrice(){
    let order_date = $("#order_date").val();
    $.ajax({
        type: "GET",
        url: `/orders/findByTicketOfOrderDate/${order_date}`
    }).done(function (tickets) {
        $.ajax({
            type: "GET",
            url : `/orders/findByOrderDate/${order_date}`
        }).done(function (orders){
            let amountTicket = 0;
            let totalMoney = 0;
            let content = "";
            for (let i = 0; i < tickets.length ; i++) {
                amountTicket++;
            }
            for (let i = 0; i < orders.length; i++) {
                totalMoney += orders[i].total_price;
                content = `
            <tr>
                <td colspan="5"><strong>Tống số vé bán ra trong ngày:</strong></td>
                <td></td>
                <td colspan="2"><strong>${amountTicket}</strong></td>
            </tr>
            <tr>
                <td colspan="5"><strong>Tống doanh thu trong ngày:</strong></td>
                <td></td>
                <td colspan="2"><strong>${totalMoney.toLocaleString('vi', {style: 'currency', currency: 'VND'})}</strong></td>
            </tr>
            `;
            }
            $("#orderList tfoot").html(content);
        })
    })
}

function getTotalProduct(id) {
    $.ajax({
        type: "GET",
        url: `/orderDetails/getAllOrderDetail/${id}`
    }).done(function (products) {
        let content1 = "";
        let content = "";
        for (let i = 0; i < products.length; i++) {
            content += `
                        <tr>
                            <td>${products[i].product_name}</td>
                            <td>${products[i].product_amount}</td>
                            <td>${products[i].price.toLocaleString('vi', {style: 'currency', currency: 'VND'})}</td>
                        </tr> 
        `;
            content1 =`
                     <tr>
                      <td colspan="2">Tổng giá vé</td>
                      <td>${products[i].order.total_product}</td>
                    </tr>
            `
        }
        $("#product-modal tbody").html(content);
        $("#product-modal tfoot").html(content1);
        $("#showModalProduct").modal('show');
    })
}

function getTotalTicket(id){
    $.ajax({
        type : "GET",
        url : `/tickets/allTicketByOder/${id}`
    }).done(function (tickets){
        let content1 = "";
        let content = "";
        for (let i = 0; i  < tickets.length ; i++) {
            content += `
                        <tr>
                            <td>${tickets[i].show.film.film_name}</td>
                            <td>${tickets[i].seat.seat_name}</td>
                            <td>${tickets[i].show.time_start}</td>
                            <td>${tickets[i].price.toLocaleString('vi', {style: 'currency', currency: 'VND'})}</td>
                        </tr> 
         `;
            content1 =`
                     <tr>
                      <td colspan="3">Tổng giá vé</td>
                      <td>${tickets[i].order.total_ticket}</td>
                    </tr>
            `
        }
        $("#ticket-modal tbody").html(content);
        $("#ticket-modal tfoot").html(content1);
        $("#showModalTicket").modal('show');
    })
}


$("#order_date").on("change",function (){
    getListOrderDate();
    getTotalAllPrice();
});

currentOrderDate();
function currentOrderDate(){
    $("#order_date").val(App.getToday())
}

getListOrderDateToday();

function getListOrderDateToday(){
    let order_date = App.getToday();
    $.ajax({
        type: "GET",
        url: `/orders/findByTicketOfOrderDate/${order_date}`
    }).done(function (tickets) {
        $.ajax({
            type: "GET",
            url : `/orders/findByOrderDate/${order_date}`
        }).done(function (orders){
            let amountTicket = 0;
            let totalMoney = 0;
            let content = "";
            for (let i = 0; i < tickets.length ; i++) {
                amountTicket++;
            }
            for (let i = 0; i < orders.length; i++) {
                totalMoney += orders[i].total_price;
                content = `
            <tr>
                <td colspan="5">Tống số vé bán ra trong ngày</td>
                <td></td>
                <td colspan="2">${amountTicket}</td>
            </tr>
            <tr>
                <td colspan="5">Tống tiền trong ngày</td>
                <td></td>
                <td colspan="2">${totalMoney.toLocaleString('vi', {style: 'currency', currency: 'VND'})}</td>
            </tr>
            `;
            }
            $("#orderList tfoot").html(content);
        })
    })
    $.ajax({
        type : "GET",
        url : `/orders/findByOrderDate/${order_date}`
    }).done(function (orders){
        let content = "";
        if(orders.length === 0){
            content = "Chưa thực hiện giao dịch"
        }else {
            for (let i = 0; i < orders.length; i++) {
                content += `
             <tr id="row${orders[i].order_id}">
                              <input hidden id="${orders[i].order_id}">
                              <td>${orders[i].order_id}</td>
                              <td>${orders[i].order_time}</td>
                              <td class="text-right"><a value="${orders[i].order_id}" class="btn modalProduct">${orders[i].total_product.toLocaleString('vi', {
                    style: 'currency',
                    currency: 'VND'
                })}</a></td>
                              <td class="text-right"><a value="${orders[i].order_id}" class="btn modalTicket">${orders[i].total_ticket.toLocaleString('vi', {style: 'currency', currency: 'VND'})}</a></td>
                              <td class="text-right">${orders[i].total_price.toLocaleString('vi', {style: 'currency', currency: 'VND'})}</td>
                              <td>${orders[i].member == null ? "None" : orders[i].member.member_name}</td>
                              <td>${orders[i].user.username}</td>
                        </tr>
                        
            `;
            }
        }
        $("#orderList tbody").html(content);
        $('.modalProduct').on("click", function () {
            let id = $(this).attr('value');
            getTotalProduct(id);
        })
        $('.modalTicket').on('click',function (){
            let id = $(this).attr('value');
            getTotalTicket(id);
        })
    })
}