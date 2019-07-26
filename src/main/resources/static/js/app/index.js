var index = {
	init : function() {
		
		var _this=this;
		$('#btn-goboard').on('click',function(){
			_this.goBoard();
		});
	},
	goBoard:function(){
		location.href="/board";
	}
//	loadBoard_content_modal:function(){
//		$("#test").load("board_content_modal.hbs");
////		var source=$("#board_content_modal").html();
////		var template=Handlebars.compile(source);
////		var html=template();
////		$("#test").append(html);
//	}
};
index.init();