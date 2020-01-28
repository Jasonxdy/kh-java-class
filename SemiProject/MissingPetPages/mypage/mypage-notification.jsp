<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title> 미씽펫 방문을 환영합니다! </title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="mypage-notification.css">


  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

  <!-- 구글 폰트 추가 -->
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">

  <!-- 토글 스위치 CDN -->
  <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
  <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

</head>

<body>


  <!-- ---
        layout: examples
        title: Dashboard Template
        extra_css: "dashboard.css"
        extra_js:
          - "https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"
          - "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"
          - "dashboard.js"
        --- -->

  <!-- <div class="navbar navbar-white fixed-top bg-white flex-md-nowrap p-0 shadow bg-transparent">
            <ul class="navbar-nav px-3">
              <li class="nav-item text-nowrap">
                <a class="nav-link" href="#">
                    <img src="img/alert_icon.png" width="5%" height="10%">
                </a>
              </li>
            </ul>
          </div>
         -->






  <div class="container-fluid">

    <div class="row">

      <div class="d-md-block bg-white sidebar" id="sidebarAll" style="z-index: 1000;">

        <div class="sidebar-sticky" id="sidebarAll" style="height: 800px;">

          <table class="table-responsive">

            <ul class="nav flex-column">
              <li class="align-items-center text-center">
                <a id="logo" href="index.jsp">
                  <img src="img/logo7.png" width=150px>
                </a>
              </li>

              <li class="nav-item align-items-center">
                <button type="button" class="btn btn-outline-primary btn-sm px-5 mt-5 p-3" id="loginBtn">로그인</button>
              </li>

              <li class="nav-item mt-5">
                <p>&nbsp;</p>
              </li>

              <li class="nav-item mt-3">
                <p>&nbsp;</p>
              </li>

              <li class="nav-item mb-1">
                <a class="nav-link active text-center mt-4 mb-1" href="#">
                  찾아주세요
                </a>
              </li>

              <li class="nav-item mb-1">
                <a class="nav-link  text-center" href="#">
                  봤어요
                </a>
              </li>

              <li class="nav-item mb-1">
                <a class="nav-link  text-center" href="#">
                  입양했어요
                </a>
              </li>

              <li class="nav-item mb-1">
                <a class="nav-link  text-center" href="../만남그후/만남그후.jsp">
                  만남 그 후
                </a>
              </li>

              <li class="nav-item mb-3">
                <a class="nav-link  text-center" href="#">
                  자유게시판
                </a>
              </li>

            </ul>
            <div class="text-center">——————————</div>


            <ul class="nav flex-column text-center mt-3">
              <li class="nav-item">
                <a class="nav-link  text-center" href="#">
                  Q&A
                </a>
              </li>

              <li class="nav-item">
                <a class="nav-link  text-center" href="#">
                  마이페이지
                  <!-- 로그인 전 화면인데 마이페이지 구현? -->
                </a>
              </li>
            </ul>


          </table>
        </div>
      </div>


      <!-- 마이페이지 내용 작성 -->
      <div class="container-fluid d-md-block" id="mypage-wrapper">

        <!--  마이페이지 Header start -->
        <div class="row ml-1 w-100" id="mypage-header">
          <h1 style="font-weight: bold;">마이페이지</h1>
          <!-- 마이페이지 Header nav -->
          <div class="row w-100 mt-2">
            <ul class="nav" id="mypage-nav">
              <li class="nav-item mr-4">
                <h5><a class="nav-link" href="mypage-main.jsp" id="mypage-profile">프로필</a></h5>
              </li>
              <li class="nav-item mr-4">
                <h5>
                  <a class="nav-link" href="mypage-writing.jsp" id="mypage-writing">작성한 글</a>
                </h5>
              </li>
              <li class="nav-item mr-4">
                <h5>
                  <a class="nav-link" href="mypage-comment.jsp" id="mypage-comment">작성한 댓글</a>
                </h5>
              </li>
              <li class="nav-item mr-4">
                <h5>
                  <a class="nav-link" href="mypage-1to1-question.jsp" id="mypage-1to1-question">1:1 문의</a>
                </h5>
              </li>
              <li class="nav-item mr-4">
                <h5>
                  <a class="nav-link" href="mypage-notification.jsp" id="mypage-notification">알림 설정</a>
                </h5>
              </li>
            </ul>
            <!-- 마이페이지 Header nav end -->

          </div>


        </div>
        <!--  마이페이지 Header end -->
        <!--  -->

        <!-- 토글 스위치 출처 : https://bootsnipp.com/snippets/GaxR2 -->
        <div class="ml-1 row" id="mypage-content-wrapper">
          <h2 class="mt-3">알림 설정</h2>

          <div class="row ml-1">

            <!-- 알림 form -->
            <form action="#">
              
              <!-- 웹페이지 알림 버튼 -->
              <div class="row">
                <div class="col-md-1 ml-2 pl-4 mt-4 mb-2">
                  <label class="switch">
                    <input type="checkbox" class="warning" id="webpage-notification" checked>
                    <span class="slider round"></span>
                  </label>
                </div>
                
                <div class="col-md-8 ml-2" style="width: 900px;">
                  <h4 class="mt-2"><b>웹페이지 알림</b></h4>
                  <h6>내 활동에 대한 웹페이지 알림 수신</h6>
                </div>
              </div>


              <!-- 댓글 알림 버튼 -->
              <div class="row">
                <div class="col-md-1 ml-5 pl-4 mt-4">
                  <label class="switch">
                    <input type="checkbox" class="warning" id="comment-notification" checked>
                    <span class="slider round"></span>
                  </label>
                </div>
                
                <div class="col-md-8 ml-2" style="width: 900px;">
                  <h5 class="mt-2">댓글 알림</h5>
                  <h6>내가 작성한 글 또는 댓글의 답글에 대한 알림 수신</h6>
                </div>
              </div>
              
              <!-- 1:1 문의 알림 버튼 -->
              <div class="row">
                <div class="col-md-1 ml-5 pl-4 mt-4 mb-5 pb-4">
                  <label class="switch">
                    <input type="checkbox" class="warning" id="1to1-notification" checked>
                    <span class="slider round"></span>
                  </label>
                </div>
                
                <div class="col-md-8 ml-2" style="width: 900px;">
                  <h5 class="mt-2">1:1 문의 알림</h5>
                  <h6>내가 작성한 1:1 문의의 답변에 대한 알림 수신</h6>
                </div>
              </div>

              <!-- 이메일 알림 버튼 -->
              <div class="row">

                <div class="col-md-1 ml-2 pl-4 mt-4 mb-2">
                  <label class="switch">
                    <input type="checkbox" class="warning" checked>
                    <span class="slider round"></span>
                  </label>
                </div>
                
                <div class="col-md-8 ml-2" style="width: 900px;">
                  <h4 class="mt-2"><b>이메일 알림</b></h4>
                  <h6>이메일을 통한 실시간 알림 수신</h6>
                </div>
              </div>

            </form>
            <!-- 알림 form end -->


          </div>


          <!-- 웹페이지 알림 on/off시 전체 on/off script -->
          <script>

            $("#webpage-notification").change(function(){
              if(!$(this).prop("checked")){
                $("#comment-notification").prop("checked", false);
                $("#1to1-notification").prop("checked", false);
              }

              console.log("웹페이지 알림 : " + $(this).prop("checked"));
              console.log("댓글 알림 : " + $("#comment-notification").prop("checked"));
              console.log("1:1문의 알림 : " + $("#1to1-notification").prop("checked"));

            });

            // $("webpage-notification>input:checkbox").change(function(){
            //   console.log("자식눌렸당");
            // });
            
              // if($("#comment-notification").prop("checked") || $("#1to1-notification").prop("checked")) {
              //   $("#webpage-notification").prop("checked", true);
              // }

          
          
          </script>












          <!-- Footer start -->
          <div id="footer">

            <footer class="footer mt-4">
              <div class="footer text-center " id="footer1">
                <span class="text-muted">
                  <b class="small"> | 상호명 : 청포도 | 대표 : 청포도 | 주소 : 서울특별시 광진구 자양로 54 2층 | 사업자 등록번호 : 206-87-05773 | Tel :
                    010-0000-0000 <br>
                    Copyright © 2019-2020 Muscat All rights Reserved.
                  </b>
                </span>

                <span class="footer mr-5 small " id="footer2">
                  <a href="#">이용약관 </a> &nbsp;
                  <a href="#">개인정보처리방침</a>
                </span>
              </div>
            </footer>

          </div>

        </div>
      </div>





      <div class="dropdown col-md-12 mt-3 fixed-top pl-3 pr-4" id="alert_icon" align="right">
        <button class="btn btn-secondary dropdown-toggle btn-sm bg-transparent" type="button" id="dropdownMenuButton"
          data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <img src="img/alert3.png" width="42">
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" id="dropdown-menu">

          <div class="dropdown-item  text-center">
            <h6 class="alertOFF"> 알람설정 수정하기
              <a href="#">
                <!-- 톱니바퀴 이미지 누를 시 알람설정 페이지로 이동 -->
                <img src="img/alarmoff.png" width="30" height="30">
              </a>
            </h6>
          </div>

          <!-- 게시글 이름 누를 시 해당 게시글로 이동 -->
          <h6 class="dropdown-header"> 댓글 알림</h6>
          <a href="#"><b>&lt;강아지를 잃어버렸습니다&gt;</b></a>게시글에 댓글이 달렸습니다.<br>
          <a href="#"><b>&lt;강아지를 잃어버렸습니다&gt;</b></a>게시글에 댓글이 달렸습니다.<br>


          <!-- 게시판에 해당 필터링 적용한 목록으로 이동? -->
          <h6 class="dropdown-header"> 실시간 알림</h6>
          <mark>찾아주세요</mark><a href="#"><b>&lt;종로구&gt;</b></a>지역에 새로운 게시글이 있습니다.<br>
          <mark>봤어요</mark><a href="#"><b>&lt;종로구&gt;</b></a>지역에 새로운 게시글이 있습니다.<br>
          <mark>봤어요</mark><a href="#"><b>&lt;말티즈&gt;</b></a>품종의 새로운 게시글이 있습니다.<br>


          <h6 class="dropdown-header"> 1:1문의 답변 알림</h6>
          <a href="#"><b>&lt;건의사항&gt;</b></a>문의사항에 답변이 달렸습니다.<br>
          <a href="#"><b>&lt;홈페이지 알람기능 문의&gt;</b></a>문의사항에 답변이 달렸습니다.<br>
          <a href="#"><b>&lt;미씽펫&gt;</b></a>문의사항에 답변이 달렸습니다.<br>



        </div>
      </div>
    </div>

    <script>
      $(document).ready(function () {

        $("#webpage-notification").change(function () {
           
          if(!$(this).prop("checked")) {
            $("#comment-notification").prop("checked", false);
            $("#1to1-notification").prop("checked", false);
          } else {
            $("#comment-notification").prop("checked", true);
            $("#1to1-notification").prop("checked", true);
          }
        });

        $("#comment-notification").change(function() {
          if($(this).prop("checked")) {
            $("#webpage-notification").prop("checked", true);
          } else if (!$(this).prop("checked") && !$("#1to1-notification").prop("checked")) {
            $("#webpage-notification").prop("checked", false);
          }
        });

        $("#1to1-notification").change(function() {
          if($(this).prop("checked")) {
            $("#webpage-notification").prop("checked", true);
          } else if (!$(this).prop("checked") && !$("#comment-notification").prop("checked")) {
            $("#webpage-notification").prop("checked", false);
          }
        });
      });

    </script>




  </div>

  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
    integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
    crossorigin="anonymous"></script>

</body>

</html>