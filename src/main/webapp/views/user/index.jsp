<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/png" href="/Assignment_Java4/assets/images/icons/favicon.png"/>
    <title>${title}</title>
    <%@include file="/common/head.jsp" %>
</head>
<body class="animsition">

<%@include file="/common/nav.jsp" %>
        
<jsp:include page="${views}"/>

<%@include file="/common/footer.jsp" %>

<!-- Back to top -->
<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
</div>

<!-- Start Script -->
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/bootstrap/js/popper.js"></script>
<script src="/Assignment_Java4/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/select2/select2.min.js"></script>
<script>
    $(".js-select2").each(function(){
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    })
</script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/daterangepicker/moment.min.js"></script>
<script src="/Assignment_Java4/assets/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/slick/slick.min.js"></script>
<script src="/Assignment_Java4/assets/js/slick-custom.js"></script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/parallax100/parallax100.js"></script>
<script>
    $('.parallax100').parallax100();
</script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<script>
    $('.gallery-lb').each(function() { // the containers for all your galleries
        $(this).magnificPopup({
            delegate: 'a', // the selector for gallery item
            type: 'image',
            gallery: {
                enabled:true
            },
            mainClass: 'mfp-fade'
        });
    });
</script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/sweetalert/sweetalert.min.js"></script>
<script>
    $('.js-addwish-b2').on('click', function(e){
        e.preventDefault();
    });

    $('.js-addwish-b2').each(function(){
        var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
        $(this).on('click', function(){
            swal(nameProduct, "is added to wishlist !", "success");

            $(this).addClass('js-addedwish-b2');
            $(this).off('click');
        });
    });

    $('.js-addwish-detail').each(function(){
        var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

        $(this).on('click', function(){
            swal(nameProduct, "is added to wishlist !", "success");

            $(this).addClass('js-addedwish-detail');
            $(this).off('click');
        });
    });

    /*---------------------------------------------*/

    $('.js-addcart-detail').each(function(){
        var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
        $(this).on('click', function(){
            swal(nameProduct, "đã được thêm vào giỏ hàng!", "success");
        });
    });

</script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        $(this).css('position','relative');
        $(this).css('overflow','hidden');
        var ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
        });

        $(window).on('resize', function(){
            ps.update();
        })
    });
</script>
<!--===============================================================================================-->
<script src="/Assignment_Java4/assets/js/main.js"></script>
<!-- End Script -->
</body>
</html>