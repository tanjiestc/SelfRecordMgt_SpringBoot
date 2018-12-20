package com.tanjie.selfrecordmgt.dao;

import static com.tanjie.selfrecordmgt.dao.WordsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.tanjie.selfrecordmgt.model.Words;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WordsMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Words> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ConstructorArgs({
        @Arg(column="spelling", javaType=String.class, jdbcType=JdbcType.VARCHAR, id=true),
        @Arg(column="trans", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="phonetic", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="tags", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="progress", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Words selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ConstructorArgs({
        @Arg(column="spelling", javaType=String.class, jdbcType=JdbcType.VARCHAR, id=true),
        @Arg(column="trans", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="phonetic", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="tags", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="progress", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<Words> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(words);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, words);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default int deleteByPrimaryKey(String spelling_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, words)
                .where(spelling, isEqualTo(spelling_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default int insert(Words record) {
        return insert(SqlBuilder.insert(record)
                .into(words)
                .map(spelling).toProperty("spelling")
                .map(trans).toProperty("trans")
                .map(phonetic).toProperty("phonetic")
                .map(tags).toProperty("tags")
                .map(progress).toProperty("progress")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default int insertSelective(Words record) {
        return insert(SqlBuilder.insert(record)
                .into(words)
                .map(spelling).toPropertyWhenPresent("spelling", record::getSpelling)
                .map(trans).toPropertyWhenPresent("trans", record::getTrans)
                .map(phonetic).toPropertyWhenPresent("phonetic", record::getPhonetic)
                .map(tags).toPropertyWhenPresent("tags", record::getTags)
                .map(progress).toPropertyWhenPresent("progress", record::getProgress)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Words>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, spelling, trans, phonetic, tags, progress)
                .from(words);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Words>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, spelling, trans, phonetic, tags, progress)
                .from(words);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default Words selectByPrimaryKey(String spelling_) {
        return SelectDSL.selectWithMapper(this::selectOne, spelling, trans, phonetic, tags, progress)
                .from(words)
                .where(spelling, isEqualTo(spelling_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Words record) {
        return UpdateDSL.updateWithMapper(this::update, words)
                .set(spelling).equalTo(record::getSpelling)
                .set(trans).equalTo(record::getTrans)
                .set(phonetic).equalTo(record::getPhonetic)
                .set(tags).equalTo(record::getTags)
                .set(progress).equalTo(record::getProgress);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Words record) {
        return UpdateDSL.updateWithMapper(this::update, words)
                .set(spelling).equalToWhenPresent(record::getSpelling)
                .set(trans).equalToWhenPresent(record::getTrans)
                .set(phonetic).equalToWhenPresent(record::getPhonetic)
                .set(tags).equalToWhenPresent(record::getTags)
                .set(progress).equalToWhenPresent(record::getProgress);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default int updateByPrimaryKey(Words record) {
        return UpdateDSL.updateWithMapper(this::update, words)
                .set(trans).equalTo(record::getTrans)
                .set(phonetic).equalTo(record::getPhonetic)
                .set(tags).equalTo(record::getTags)
                .set(progress).equalTo(record::getProgress)
                .where(spelling, isEqualTo(record::getSpelling))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: words")
    default int updateByPrimaryKeySelective(Words record) {
        return UpdateDSL.updateWithMapper(this::update, words)
                .set(trans).equalToWhenPresent(record::getTrans)
                .set(phonetic).equalToWhenPresent(record::getPhonetic)
                .set(tags).equalToWhenPresent(record::getTags)
                .set(progress).equalToWhenPresent(record::getProgress)
                .where(spelling, isEqualTo(record::getSpelling))
                .build()
                .execute();
    }
}