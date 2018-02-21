package spms.vo;

import java.util.Date;

public class Project {
    protected int no; // 프로젝트 일련번호
    protected String name; // 프로젝트명
    protected String content; // 프로젝트 설명
    protected Date startDate; // 프로젝트 시작일
    protected Date endDate; // 프로젝트 종료일
    protected int state; // 프로젝트 상태
    protected Date createDate; // 글 작성일
    protected String tags; // 프로젝트에 관한 태그들

    public int getNo() {
        return no;
    }

    public Project setNo(int no) {
        this.no = no;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Project setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Project setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Project setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public int getState() {
        return state;
    }

    public Project setState(int state) {
        this.state = state;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Project setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Project setTags(String tags) {
        this.tags = tags;
        return this;
    }
}
