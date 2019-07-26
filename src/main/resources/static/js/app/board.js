
var main = {
	init : function() {
		var _this = this;
		$('#btn-save').on('click', function() {
			_this.save();
		});
		$('.tbtn').on('click', function(event) {
			_this.load(event);
		});
		$('#comment_regist_btn').on('click',function(){
			_this.comment_save();
		});
		$('#detailPostsModal').on('hidden.bs.modal',function(){
			_this.reset_data();
		});
		
	},
	reset_data:function(){
		$('#comment_list_div').empty();
	},
	comment_save :function(){
		var _this=this;
		console.log($('#post_id').val());
		var data={
				posts : {
					id : $('#post_id').val()
				},
				content : $('#comment_content').val(),
				author : $('#comment_author').val(),
				pwd : $('#comment_pwd').val()
		};
		$.ajax({
			type:'POST',
			url:'/comments',
			contentType:'application/json; charset=utf-8',
			data: JSON.stringify(data)
		})
		.done(function(data){
			$('#comment_content').val('');
			$('#comment_author').val('');
			$('#comment_pwd').val('');
			_this.reset_data();
			_this.load(0,$('#post_id').val());
			alert('댓글이 등록되었습니다');
			
		})
		.fail(function(err){
			alert(err);
			console.log(err);
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
			data : JSON.stringify(data)
		})
		.done(function() {
			location.reload();
		}).fail(function(error) {
			alert(error);
		});
	},
	load : function(t,tt) {
		var data;
		if(tt>0){
			data={
					pid : tt
			}
		}
		else{
			var d=t.target;
			data={
					pid:d.value
			}
		}
//		var d = t.target;
//		var data = {
//			pid : d.value
//		};

		$.ajax({
			type : 'POST',
			url : '/detail',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data)
		}).done(function(data) {
			$('#detailcontent').val(data.content);
			$('#detailtitle').val(data.title);
			$('#post_id').val(data.id);
			for(var i=0;i<data.comments.length;i++){
				var $div = $('#comment_list_div');
				var hbs='<div style="margin-top:4%">' 
					+'<textarea class="comment_textarea" rows="3"'
					+'>'+data.comments[i].content+'</textarea>'
					+'<div style="margin-top:1%;"><label class="comment_author_label">작성자 '+data.comments[i].author+'</label>'
					+'<button value="'+data.comments[i].id+'" class="btn btn-secondary comment_delete_btn" type="button" id="comment_delete_btn">삭제</button>'
                	+'<input class="comment_delete_input" type="text" placeholder="비밀번호" id="comment_delete_pwd" maxlength="4"/>'
					+'</div></div>'
				$(hbs).appendTo($div);
			}
			$('.comment_textarea').css("width","96%");
			$('.comment_delete_btn').css({'margin-right':'3.9%','float':'right'});
			$('.comment_delete_input').css({'margin-right':'4.5%','float':'right'});
			$('.comment_author_label').css('width','30%');
			$('.comment_delete_btn').on("click",function(ev){
				var tar=ev.target;
				if($(tar).next().val()==''){
					alert('Please insert password!');
				}
				else{
					var cmdata={
							id :tar.value,
							pwd :$(tar).next().val()
					};
					$.ajax({
						type : 'POST',
						url : '/comments/delete',
						dataType:'text',
						contentType : 'application/json; charset=utf-8',
						data:JSON.stringify(cmdata)
					}).done(function(res){
						if(res=='success'){
							$(tar).parent().parent().remove();
						}
						else{
							alert('Please enter the correct password!');
						}
					}).fail(function(error){
						alert('comment delete ERROR!\ncheck console log');
						console.log(error);
					});
				}
			});
		}).fail(function(error) {
			alert(error);
		});
	}

};

main.init();