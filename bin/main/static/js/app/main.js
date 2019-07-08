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
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data)
		}).done(function() {
			alert('Registed');
			location.reload();
		}).fail(function(error) {
			alert(error);
		});
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