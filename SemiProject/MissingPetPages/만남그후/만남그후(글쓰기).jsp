<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title> 미씽펫 방문을 환영합니다! </title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="index copy.css">
  <link rel="stylesheet" href="만남그후(글쓰기).css">


  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

</head>

<body>





  <div class="container-fluid">
    <div class="row" id="row">
      



      <div class="d-md-block bg-white sidebar col-md-2" id="sidebarAll" style="height: 900px;">
        <div class="sidebar-sticky" id="sidebarAll">

          <table class="table-responsive">

            <ul class="nav flex-column">
              <li class="align-items-center text-center">
                <a id="logo" href="index.html">
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
                <a class="nav-link  text-center" href="입양했어요.html">
                  입양했어요
                </a>
              </li>

              <li class="nav-item mb-1">
                <a class="nav-link  text-center" href="#">
                  만남 그 후
                </a>
              </li>

              <li class="nav-item mb-5">
                <a class="nav-link  text-center" href="자유게시판.html">
                  자유게시판
                </a>
              </li>

            </ul>


            <div class="text-center">——————————</div>


            <ul class="nav flex-column mt-5 text-center">
              <li class="nav-item">
                <a class="nav-link  text-center" href="#">
                  Q&A
                </a>
              </li>

              <li class="nav-item mt-4">
                <a class="nav-link  text-center" href="../mypage/mypage-main.html">
                  마이페이지
                </a>
              </li>
            </ul>
          </table>
        </div>
      </div> <!-- sidebarAll 종료-->




            <!--contents 시작-->

            <div class="col-md-10 mt-4" id="con">
          
            <div id="page-content-wrapper" >
              <div class="card bg-light shadow mb-5 ml-4 mt-5 ">
                <div class="card-body">
                  

                    <!-- 이 안에다가 작성하세요!!!!!!!! -->

            
            <div class="container">
              <div class="row">
            
                <section class="content">
                  <h2 id="title-top"><a href="#">만남 그 후 글쓰기</a></h2>
                  <hr>
                  <div class="col-md-12 col-md-offset-2" id="panelwrap" >

                    <div id="title">
                      <!-- <label>말머리 :</label>
                      &nbsp;
                     <select>
                       <option>&nbsp;&nbsp;&nbsp;공지&nbsp;&nbsp;&nbsp;</option>
                       <option>잡담</option>
                       <option>자유</option>
                     </select>
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
                    <label> 제목 :</label>
                    &nbsp;
                    <input type="text" size="114">
                    </div>
                   <div id="pic">

                   </div>
                   <div>
                    <label><b>영상 첨부</b></label>
                    &nbsp;&nbsp;
                    <input type="text" size="30">
                    &nbsp;&nbsp;&nbsp;
                    <label><b>사진 첨부</b></label>
                    &nbsp;&nbsp;
                    <input type="file">
                   </div>
                   <div id="top-bt">
                    <button type="submit" class="btn btn-primary mr-5">작성완료</button>
                    <button type="submit" class="btn btn-primary ml-5">취소</button>
                   </div>
                   



                  </div>

                  <!--오른쪽 배너-->
                 

                  <!--하단-->
                   
                 


                </section>
                
              </div>
            </div> <!-- container 종료-->






            <!-- 이 안에다가 작성하세요!!!!!!!! -->
           </div>
          </div>
          </div>


          
          </div> <!-- #con 종료-->

            <!--contents 종료-->






      <div class="dropdown col-md-12 mt-2 fixed-top pl-3 pr-4" id="alert_icon" align="right">
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
      </div> <!-- 상단 알람 종료-->




   
   
   
    </div>   <!-- #row 종료-->






    <div id="footer" class="mt-4">

      <footer class="footer">
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
    
      </div> <!--#footer 종료-->








  </div>  <!-- .container-fluid 종료 -->





  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
    integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
    crossorigin="anonymous"></script>

</body>

</html>