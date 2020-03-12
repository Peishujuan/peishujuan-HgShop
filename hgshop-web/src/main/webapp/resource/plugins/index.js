$(function(){
	//淡出效果 750毫秒
	  $(".loading").fadeOut(750);
	 });

 /* Sidebar Menu*/
 $(document).ready(function () {
   $('.nav > li > a').click(function(){
     if ($(this).attr('class') != 'active'){
       $('.nav li ul').slideUp();
       $(this).next().slideToggle();
       $('.nav li a').removeClass('active');
       $(this).addClass('active');
     }
   });
 });

 /* Sidebar Show-Hide On Mobile */
 $(document).ready(function(){
     $(".sidebar-open-button-mobile").click(function(){
         $(".sidebar").toggle(150);
     });
 });

 /* Sidebar Show-Hide */
 $(document).ready(function(){

     $('.sidebar-open-button').on('click', function(){
         if($('.sidebar').hasClass('hidden')){
             $('.sidebar').removeClass('hidden');
             $('.content').css({
                 'marginLeft' : 250
             });  
         }else{
             $('.sidebar').addClass('hidden');
             $('.content').css({
                 'marginLeft' : 0
             });    
         }
     });

 });