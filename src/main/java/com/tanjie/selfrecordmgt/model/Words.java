package com.tanjie.selfrecordmgt.model;

import lombok.ToString;

import javax.annotation.Generated;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table words
 */
@ToString
public class Words {
    /**
     * Database Column Remarks:
     *   单词的拼写
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.spelling")
    private String spelling;

    /**
     * Database Column Remarks:
     *   中文释义
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.trans")
    private String trans;

    /**
     * Database Column Remarks:
     *   音标
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.phonetic")
    private String phonetic;

    /**
     * Database Column Remarks:
     *   分类
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.tags")
    private String tags;

    /**
     * Database Column Remarks:
     *   状态 -1 未复习 0 复习中 1 复习完毕
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.progress")
    private String progress;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    public Words(String spelling, String trans, String phonetic, String tags, String progress) {
        this.spelling = spelling;
        this.trans = trans;
        this.phonetic = phonetic;
        this.tags = tags;
        this.progress = progress;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    public Words() {
        super();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.spelling")
    public String getSpelling() {
        return spelling;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.spelling")
    public void setSpelling(String spelling) {
        this.spelling = spelling == null ? null : spelling.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.trans")
    public String getTrans() {
        return trans;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.trans")
    public void setTrans(String trans) {
        this.trans = trans == null ? null : trans.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.phonetic")
    public String getPhonetic() {
        return phonetic;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.phonetic")
    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic == null ? null : phonetic.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.tags")
    public String getTags() {
        return tags;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.tags")
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.progress")
    public String getProgress() {
        return progress;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.progress")
    public void setProgress(String progress) {
        this.progress = progress == null ? null : progress.trim();
    }
}