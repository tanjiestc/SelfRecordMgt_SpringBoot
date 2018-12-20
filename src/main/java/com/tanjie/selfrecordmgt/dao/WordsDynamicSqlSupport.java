package com.tanjie.selfrecordmgt.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class WordsDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    public static final Words words = new Words();

    /**
     * Database Column Remarks:
     *   单词的拼写
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.spelling")
    public static final SqlColumn<String> spelling = words.spelling;

    /**
     * Database Column Remarks:
     *   中文释义
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.trans")
    public static final SqlColumn<String> trans = words.trans;

    /**
     * Database Column Remarks:
     *   音标
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.phonetic")
    public static final SqlColumn<String> phonetic = words.phonetic;

    /**
     * Database Column Remarks:
     *   分类
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.tags")
    public static final SqlColumn<String> tags = words.tags;

    /**
     * Database Column Remarks:
     *   状态 -1 未复习 0 复习中 1 复习完毕
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: words.progress")
    public static final SqlColumn<String> progress = words.progress;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    public static final class Words extends SqlTable {
        public final SqlColumn<String> spelling = column("spelling", JDBCType.VARCHAR);

        public final SqlColumn<String> trans = column("trans", JDBCType.VARCHAR);

        public final SqlColumn<String> phonetic = column("phonetic", JDBCType.VARCHAR);

        public final SqlColumn<String> tags = column("tags", JDBCType.VARCHAR);

        public final SqlColumn<String> progress = column("progress", JDBCType.VARCHAR);

        public Words() {
            super("words");
        }
    }
}