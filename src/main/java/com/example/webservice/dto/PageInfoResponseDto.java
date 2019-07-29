package com.example.webservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PageInfoResponseDto {
	
	private int pageNum;
	private long totalCount;
	private int showNum;
	private int totalPage;
	private int startPage;
	private int endPage;
	private int next;
	private int prev;
	
	@Builder
	public PageInfoResponseDto(int pageNum,long totalCount,int totalPage,int showNum) {
		this.pageNum=pageNum;
		this.totalCount=totalCount;
		this.showNum=showNum;
		this.totalPage=(int) (totalCount%showNum==0?totalCount/showNum:totalCount/showNum+1);
		this.startPage=(pageNum%showNum==0?((pageNum/showNum)-1)*showNum+1:
								(pageNum/showNum*showNum)+1);
		this.endPage=this.calEndPage();
		this.next=this.calNext();
		this.prev=this.calPre();
		
	}
	public int calEndPage() {
		if(this.startPage+4<=totalPage) {
			return this.startPage+4;
		}
		else {
			return totalPage;
		}
	}
	public int calNext() {
		if(this.totalPage>this.endPage) {
			return this.endPage+1;
		}
		else {
			return 0;
		}
	}
	public int calPre() {
		if(this.endPage<=this.showNum) {
			return 0;
		}
		else {
			return this.startPage-1;
		}
	}
}
