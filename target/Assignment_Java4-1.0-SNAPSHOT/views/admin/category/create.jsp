<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 05/04/2022
  Time: 9:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row ">
    <h3 class="mt-2 text-center">Thêm danh mục</h3>
</div>
<div class="container">
    <div class="row">
        <div class="col-6 m-auto">
            <form action="/Assignment_Java4/admin/category/store" method="post">
                <div class="form-group">
                    <label for="category-name">Tên danh mục</label>
                    <input type="text" class="form-control" name="category-name" id="category-name">
                </div>
                <button type="submit" class="btn btn-primary mt-3" style="float:right">Thêm</button>
            </form>
        </div>
    </div>
</div>
