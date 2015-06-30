$(document).ready(function() {
   // when hover over any div inside div with class=photos, find
   // the grey image and change its opacity to 0
   // opacity = 0 - invisible, opacity = 1 - completely opaque
   $('.photos div').hover(
	  function() {
	  	  $(this)
	  	  .find('img.nocolor')
	  	  .stop()
	  	  .animate({ 'opacity': '0' }, 400);
   	  },
	  function() {
	  	  $(this)
	  	  .find('img.nocolor')
	  	  .stop()
	  	  .animate({ 'opacity': '1' }, 400);
      });

});