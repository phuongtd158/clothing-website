<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
    <!--========== BOX ICONS ==========-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">

    <!--========== CSS ==========-->
    <link rel="stylesheet" href="/Assignment_Java4/assets/admin/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="/Assignment_Java4/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.css"
          integrity="sha512-oe8OpYjBaDWPt2VmSFR+qYOdnTjeV9QPLJUeqZyprDEQvQLJ9C5PCFclxwNuvb/GQgQngdCXzKSFltuHD3eCxA=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"
            integrity="sha512-lbwH47l/tPXJYG9AcFNoJaTMhGvYWhVM9YI43CT+uteTRRaiLCui8snIgyAN8XWgNjNhCqlAUdzZptso6OCoFQ=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"></script>
    <style>
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<!--========== HEADER ==========-->
<header class="header">
    <div class="header__container">
        <%--        <img src="/Assignment_Java4/assets/admin/img/perfil.jpg" alt="" class="header__img">--%>
        <a href="#" class="header__logo">Admin</a>
        <span>Xin chào, ${sessionScope.user.fullName}</span>
        <div class="header__toggle">
            <i class='bx bx-menu' id="header-toggle"></i>
        </div>
    </div>
</header>

<!--========== NAV ==========-->
<div class="nav1" id="navbar">
    <nav class="nav__container">
        <div>
            <a href="#" class="nav__link nav__logo">
                <i class='bx bxs-disc nav__icon'></i>
                <span class="nav__logo-name">Admin</span>
            </a>

            <div class="nav__list">
                <div class="nav__items">
                    <h3 class="nav__subtitle">Quản lý</h3>
                    <a href="/Assignment_Java4/admin/home" class="nav__link active">
                        <i class='bx bx-home nav__icon'></i>
                        <span class="nav__name">Trang chủ</span>
                    </a>

                    <div class="nav__dropdown">
                        <a href="#" class="nav__link">
                            <i class='bx bx-package nav__icon'></i>
                            <span class="nav__name">Sản phẩm</span>
                            <i class='bx bx-chevron-down nav__icon nav__dropdown-icon'></i>
                        </a>

                        <div class="nav__dropdown-collapse">
                            <div class="nav__dropdown-content">
                                <a href="/Assignment_Java4/admin/category/list" class="nav__dropdown-item">Danh mục</a>
                                <a href="/Assignment_Java4/admin/product/list" class="nav__dropdown-item">Sản phẩm</a>
                                <div class="nav__dropdown">
                                    <a href="#" class="nav__link">
                                        <span class="nav__name">Thuộc tính</span>
                                    </a>
                                    <div class="nav__dropdown-collapse mt-0">
                                        <div class="nav__dropdown-content">
                                            <a href="/Assignment_Java4/admin/color/list" class="nav__dropdown-item">Color</a>
                                            <a href="/Assignment_Java4/admin/size/list"
                                               class="nav__dropdown-item">Size</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <a href="/Assignment_Java4/admin/user/list" class="nav__link">
                        <i class='bx bx-user nav__icon'></i>
                        <span class="nav__name">Người dùng</span>
                    </a>

                    <a href="/Assignment_Java4/admin/order/list" class="nav__link">
                        <i class='bx bx-cart nav__icon'></i>
                        <span class="nav__name">Đơn hàng</span>
                    </a>
                </div>

                <%--                <div class="nav__items">--%>
                <%--                    <h3 class="nav__subtitle">Menu</h3>--%>

                <%--                    <div class="nav__dropdown">--%>
                <%--                        <a href="#" class="nav__link">--%>
                <%--                            <i class='bx bx-bell nav__icon'></i>--%>
                <%--                            <span class="nav__name">Notifications</span>--%>
                <%--                            <i class='bx bx-chevron-down nav__icon nav__dropdown-icon'></i>--%>
                <%--                        </a>--%>

                <%--                        <div class="nav__dropdown-collapse">--%>
                <%--                            <div class="nav__dropdown-content">--%>
                <%--                                <a href="#" class="nav__dropdown-item">Blocked</a>--%>
                <%--                                <a href="#" class="nav__dropdown-item">Silenced</a>--%>
                <%--                                <a href="#" class="nav__dropdown-item">Publish</a>--%>
                <%--                                <a href="#" class="nav__dropdown-item">Program</a>--%>
                <%--                            </div>--%>
                <%--                        </div>--%>

                <%--                    </div>--%>

                <%--                    <a href="#" class="nav__link">--%>
                <%--                        <i class='bx bx-compass nav__icon'></i>--%>
                <%--                        <span class="nav__name">Explore</span>--%>
                <%--                    </a>--%>
                <%--                    <a href="#" class="nav__link">--%>
                <%--                        <i class='bx bx-bookmark nav__icon'></i>--%>
                <%--                        <span class="nav__name">Saved</span>--%>
                <%--                    </a>--%>
                <%--                </div>--%>
            </div>
        </div>

        <a href="/Assignment_Java4/logout" class="nav__link nav__logout">
            <i class='bx bx-log-out nav__icon'></i>
            <span class="nav__name">Đăng xuất</span>
        </a>
    </nav>
</div>

<!--========== CONTENTS ==========-->
<main>
    <section style="margin-top: 50px">
        <jsp:include page="${viewAdmin}"/>
    </section>

</main>

<script src="/Assignment_Java4/assets/admin/js/main.js"></script>
<script>
    $(document).ready(function () {
        $('.js-example-basic-multiple').select2();
    });
</script>
</body>
</html>
