$(document).ready(function() {
	var changed = false;
	function sortTable(f,n){
		var rows = $('#mytable tbody tr').get();

		rows.sort(function(a, b) {
			var A = getVal(a);
			var B = getVal(b);
			var nul = n;
			if(A < B) {
				return -1*f;
			}
			if(A > B) {
				return 1*f;
			}
			return 0;
		});

		function getVal(elm){
			var v = $(elm).children('td').eq(n).text().toUpperCase();
			if(v=="" && f == 1){ 
				return 99999999999999999;
			}
			if($.isNumeric(v)){
				v = parseInt(v,10);
			}
			return v;
		}

		$.each(rows, function(index, row) {
			$('#mytable').children('tbody').append(row);
		});
	}
	
	var f = 1;
	$('th').click(function(){
		f *= -1;
		var n = $(this).prevAll().length;
		sortTable(f,n);
	});
	
	$(function()	{
		$('td').click(function(e)	{
			//ловим элемент, по которому кликнули
			var t = e.target || e.srcElement;
			//получаем название тега
			var elm_name = t.tagName.toLowerCase();
			//если это инпут - ничего не делаем
			if(elm_name == 'input')	{return false;}
			var value = $(this).html();
			var code = '<input type="text" id="edit" value="'+value+'" />';
			$(this).empty().append(code);
			$('#edit').focus();
			$('#edit').blur(function()	{
				var val = $(this).val();				
				if(value != val) {					
					changed = true;
				} 
				$(this).parent().empty().html(val);				
			});
		});
	});

	$(window).keydown(function(event){
		//ловим событие нажатия клавиши
		if(event.keyCode == 13) {	//если это Enter
			$('#edit').blur();	//снимаем фокус с поля ввода
		}
	});	
	
	$(window).bind("beforeunload", function() { 
		if(changed) {
			return confirm("Сохранить изменения?"); 
		}
	})
});