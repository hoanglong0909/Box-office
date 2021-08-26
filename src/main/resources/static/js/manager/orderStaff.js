App.getUser()

function getByUserRoleStaff(){
    $.ajax({
        type: "GET",
        url: `/orders/findByStaff/${3}`
    }).done(function (users){
        let content = "";
        for (let i = 0; i < users.length; i++) {
            content += `
                <option value="${users[i].id}">${users[i].username}</option>  
            `
        }
        $("#username").html(content);
    })
}

getByUserRoleStaff();

function searchByStaffName(){
    let order_date = $("#order_date").val();
    let id = $("#username option:selected").attr("value");

    console.log(order_date)
    console.log(id);
    $.ajax({
        type: "GET",
        url: `/orders/searchByStaffName/${id}`
    }).done(function (orders){
        console.log(orders)
        let total = 0;
        let content = "";
        let content1 = "";
        for (let i = 0; i < orders.length ; i++) {
            if(orders[i].order_date === order_date){
                total += orders[i].total_price
                content += `
                    <tr>
                        <td>${orders[i].order_id}</td>
                        <td>${orders[i].order_date}</td>
                        <td>${orders[i].order_time}</td>
                        <td>${orders[i].total_price.toLocaleString('vi', {style : 'currency', currency : 'VND'})}</td>
                        <td>${orders[i].user.username}</td>
                    </tr>
                  
                `;
            }
        }
        content1 += `
            <tr>
                <td colspan="4"><strong>Tổng doanh thu của nhân viên:</strong></td>
                <td><strong>${total.toLocaleString('vi', {style : 'currency', currency : 'VND'})}</strong></td>
            </tr>        
        `;

        $("#orderByStaff tbody").html(content);
        $("#orderByStaff tfoot").html(content1);
    })
}

$("#search-button").on("click",searchByStaffName);