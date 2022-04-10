<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row ">
    <h3 class="mt-2 text-center">Cập nhật kích thước</h3>
</div>
<div class="container">
    <div class="row">
        <div class="col-6 m-auto">
            <div class="row">
                <c:if test="${!empty sessionScope.errorMess}">
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                            ${sessionScope.errorMess}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <c:remove var="errorMess" scope="session"/>
                </c:if>
            </div>
            <form action="/Assignment_Java4/admin/size/update?id=${size.id}" method="post">
                <div class="form-group">
                    <label for="size-name">Tên kích thước</label>
                    <input type="text" class="form-control" name="size-name" id="size-name"
                           value="${size.sizeName}">
                </div>
                <button type="submit" class="btn btn-primary mt-3" style="float:right">Cập nhật</button>
            </form>
        </div>
    </div>
</div>
