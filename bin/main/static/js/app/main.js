
var main = {
	init : function() {
		var _this = this;
		$('#btn-save').on('click', function() {
			_this.save();
		});
		$('.tbtn').on('click', function(event) {
			_this.load(event);
		});
	},
	save : function() {
		var data = {
			title : $('#title').val(),
			author : $('#author').val(),
			content : $('#content').val()
		};

		$.ajax({
			type : 'POST',
			url : '/posts',
//			dataType : 'text',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data),
			success :function(re){
				location.reload();
			},
			error : function(err){
				alert(err);
			}
		});
//		.done(function() {
//			location.href='/';
//		}).fail(function(error) {
//			alert(JSON.stringify(error));
//		});
	},
	load : function(t) {
		var d = t.target;
		var data = {
			pid : d.value
		};

		$.ajax({
			type : 'POST',
			url : '/detail',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data)
		}).done(function(dd) {
			$('#detailcontent').val(dd.content);
			$('#detailtitle').val(dd.title);
			//location.reload();
		}).fail(function(error) {
			alert(error);
		});
	}

};

main.init();