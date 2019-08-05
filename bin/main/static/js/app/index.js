var index = {
	init : function() {
		
		var _this=this;
		$('#btn-goboard').on('click',function(){
			_this.goBoard();
		});
		$('#kakao_login_btn').on('click',function(){
			_this.kakao_login_call();
		});
		$('#main_title').on('click',function(){
			location.href='/';
		});
		$('#logout_btn').on('click',function(){
			$.ajax({
				type:'GET',
				url:'/logout',
			}).done(function(){
				location.reload();
			});
			
		});
	},
	goBoard:function(){
		location.href="/board/1";
	},
	kakao_login_call:function(){
		location.href='https://kauth.kakao.com/oauth/authorize?client_id=747c264aec4ef413486e0c340bd9d147&redirect_uri='
			+'http://'+$(location).attr('host')+'/kakaoSuccess'+'&response_type=code';
	}
	
};
index.init();