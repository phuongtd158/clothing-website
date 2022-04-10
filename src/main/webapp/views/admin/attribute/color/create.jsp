<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row ">
    <h3 class="mt-2 text-center">Thêm danh màu sắc</h3>
</div>
<div class="container">
    <div class="row">
        <div class="col-6 m-auto">
            <form action="/Assignment_Java4/admin/color/store" method="post">
                <div class="form-group">
                    <label for="color-name">Tên màu sắc</label>
                    <input type="text" class="form-control" name="color-name" id="color-name">
                </div>
                <button type="submit" class="btn btn-primary mt-3" style="float:right">Thêm</button>
            </form>
        </div>
    </div>
</div>
