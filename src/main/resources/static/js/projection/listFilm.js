function checkAvailable(){
    $.ajax({
        type: "GET",
        url: "/films/checkAvailable"
    }).done(getAllFilm)
}

function getAllFilm(){
    $.ajax({
        type: "GET",
        url: "/films/allFilm"
    }).done(function (films){
        let content = "";
        for (let i = films.length-1; i >= 0; i--) {
            content += `
                            <div class="col-md-6" >
                                <div class="ml-1 mr-1 row g-0 border rounded overflow-hidden flex-md-row  mb-4 shadow-sm h-md-300  position-relative ">
                                    <div class="col-auto d-none d-lg-block">
                                     <img style="object-fit: cover"  width="220px" height="310px" src= "/uploads/${films[i].image}"  alt="${films[i].film_name}">
                                    </div>
                                    <div class="col p-4 d-flex flex-column position-static">
                                    
                                        <div class="btn buttonStatus ${films[i].status ? "btn" : "btn-secondary" }">${films[i].status ?'':"Phim đã hết hạn"}</div>
                                    
                                        <div>
                                        <h4 class="mb-5"><b>${films[i].film_name}</b></h4>
                                        </div>
                                        <div class="mb-2"><strong>Thời lượng:</strong></br> ${films[i].duration}</div>
                                        <p class=" mb-2">
                                           <strong>Ngày công chiếu:</strong></br> ${films[i].rel_date}
                                        </p>
                                        <p class=" mb-2">
                                           <strong>Ngày kết thúc:</strong></br> ${films[i].exp_date}
                                        </p>
                                          <p class="mb-2">
                                           <strong>Lượt xem: ${films[i].admit}</strong></br>
                                        </p>
                                        <a href="/films/edit-form/${films[i].film_id}" value="${films[i].film_id}" type="submit" class="edit-button">
                                        <i class="edit-button" style="color: #0a53be">Chi tiết</i></a>
                                        </a> 
                                    </div>
                             
                                </div>
                            </div>
                `;
        }
        $("#listFilm tbody").html(content);
    })
}

App.getUser();

checkAvailable();