package com.example.demo.base;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageInfo<T> extends ResultSet {


    private int pageIndex; // 当前页
    private int pageSize; // 显示数量
    private int pages; // 总页数
    private Long total; // 总条数
    private List<T> data; // 列表数据

    public PageInfo(int code, String msg){
        super.resultCode = code;
        super.resultMsg = msg;
    }
    public PageInfo(Page<T> page){
        super.resultCode = ResultSet.RESULT_CODE_TRUE;
        super.resultMsg = "查询成功";
        if(!page.isEmpty()){
            if(page.getSize() == 0){
                this.pageSize = 10;
            }else{
                this.pageSize = page.getSize();
            }
            this.pageIndex = page.getNumber()+1;
            this.pages = page.getTotalPages();
            this.total = page.getTotalElements();
            this.data = page.getContent();
        }else{
            this.pageSize = 10;
            this.pageIndex=1;
        }
    }
    public PageInfo(int pageIndex, int pageSize, int pages, long total, List<T> data){
        super.resultCode = ResultSet.RESULT_CODE_TRUE;
        super.resultMsg = "查询成功";
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pages = pages;
        this.total = total;
        this.data = data;
    }
}
