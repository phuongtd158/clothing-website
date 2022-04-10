<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row ">
    <h3 class="mt-2 mb-4 text-center">Cập nhật người dùng</h3>
</div>
<div class="container">
    <div class="row">
        <c:if test="${!empty sessionScope.errorMess}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    ${sessionScope.errorMess}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <c:remove var="errorMess" scope="session"/>
        </c:if>
    </div>
    <form action="/Assignment_Java4/admin/user/update?id=${user.id}" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col-3">
                <img class="img-fluid" id="imgPreview" src="/Assignment_Java4/upload/${user.avatar}" alt=""
                     style="border:1px solid #ccc;">
                <label>Hình ảnh</label>
                <input type="file" id="photo" class="form-control" name="image">
            </div>
            <div class="col-9">
                <div class="form-group">
                    <label>Họ tên</label>
                    <input type="text" class="form-control" name="fullName" value="${user.fullName}">
                </div>
                <div class="form-group mt-3">
                    <label class="mr-2">Giới tính</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="inlineRadio1"
                               value="1" ${user.gender == 1 ? 'checked' : ''}>
                        <label class="form-check-label" for="inlineRadio1">Nam</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="inlineRadio2"
                               value="0" ${user.gender != 1 ? 'checked' : ''}>
                        <label class="form-check-label" for="inlineRadio2">Nữ</label>
                    </div>
                </div>
                <div class="form-group mt-3">
                    <label>Địa chỉ</label>
                    <input type="text" class="form-control" name="address" value="${user.address}">
                </div>
                <div class="row">
                    <div class="col-6">
                        <div class="form-group mt-3">
                            <label>Email</label>
                            <input type="email" class="form-control" name="email" value="${user.email}" disabled>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group mt-3">
                            <label>Số điện thoại</label>
                            <input type="text" class="form-control" name="phoneNumber" value="${user.phoneNumber}">
                        </div>
                    </div>
                </div>
                <div class="form-group mt-3">
                    <label>Vai trò</label>
                    <select class="form-select" name="roleId">
                        <c:forEach var="role" items="${listRole}">
                            <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button class="btn btn-primary mt-3" style="float:right">Cập nhật</button>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(() => {
        $('#photo').change(function () {
            const file = this.files[0];
            if (file) {
                let reader = new FileReader();
                reader.onload = function (event) {
                    console.log(event.target.result);
                    $('#imgPreview').attr('src', event.target.result);
                }
                reader.readAsDataURL(file);
            }
        });
    });
</script>


