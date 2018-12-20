package com.tanjie.selfrecordmgt.dao;

import static com.tanjie.selfrecordmgt.dao.BookDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.tanjie.selfrecordmgt.model.Book;
import java.util.List;
import javax.annotation.Generated;

import org.apache.ibatis.annotations.*;
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
public interface BookMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true, keyProperty="record.id")
    int insert(InsertStatementProvider<Book> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="author", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="price", javaType=Long.class, jdbcType=JdbcType.DECIMAL),
        @Arg(column="quantity", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    Book selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="author", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="price", javaType=Long.class, jdbcType=JdbcType.DECIMAL),
        @Arg(column="quantity", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    List<Book> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(book);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, book);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, book)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Book record) {
        return insert(SqlBuilder.insert(record)
                .into(book)
//                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(author).toProperty("author")
                .map(price).toProperty("price")
                .map(quantity).toProperty("quantity")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    Integer insertReturnId(Book book);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Book record) {
        return insert(SqlBuilder.insert(record)
                .into(book)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(author).toPropertyWhenPresent("author", record::getAuthor)
                .map(price).toPropertyWhenPresent("price", record::getPrice)
                .map(quantity).toPropertyWhenPresent("quantity", record::getQuantity)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Book>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, author, price, quantity)
                .from(book);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Book>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, author, price, quantity)
                .from(book);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Book selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, author, price, quantity)
                .from(book)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Book record) {
        return UpdateDSL.updateWithMapper(this::update, book)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(author).equalTo(record::getAuthor)
                .set(price).equalTo(record::getPrice)
                .set(quantity).equalTo(record::getQuantity);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Book record) {
        return UpdateDSL.updateWithMapper(this::update, book)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(author).equalToWhenPresent(record::getAuthor)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(quantity).equalToWhenPresent(record::getQuantity);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Book record) {
        return UpdateDSL.updateWithMapper(this::update, book)
                .set(name).equalTo(record::getName)
                .set(author).equalTo(record::getAuthor)
                .set(price).equalTo(record::getPrice)
                .set(quantity).equalTo(record::getQuantity)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Book record) {
        return UpdateDSL.updateWithMapper(this::update, book)
                .set(name).equalToWhenPresent(record::getName)
                .set(author).equalToWhenPresent(record::getAuthor)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(quantity).equalToWhenPresent(record::getQuantity)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}