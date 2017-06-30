package com.qatang.team.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author qatang
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    void flush();

    <S extends T> S saveAndFlush(S s);

    /**
     * 锁行读写的方式通过ID获取对象
     * @param id 对象ID
     * @return 对应的对象
     */
    T findOneForUpdate(ID id);

    /**
     * 不带 count 执行分页查询, 此时返回的 total 数即本次查询结果数量
     * Returns a {@link Page} of entities matching the given {@link Specification}.
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<T> findAllWithoutCountQuery(Specification<T> spec, Pageable pageable);

    /**
     * 清除所有被entityManager管理的entity
     * 操作结果等于detachAll
     * 警告: 不只是清除当前类型的entity, 所有已经载入的entity都会被清除, 除非你知道在干什么, 否则不要调用此方法
     */
    void clear();

    /**
     * 将指定entity解除entityManager管理
     * @param s 被管理的entity
     */
    <S extends T> void detach(S s);

    /**
     * 批量detach操作
     * @param iterable 可遍历的entity集合
     * @param <S> entity类型
     */
    <S extends T> void detach(Iterable<S> iterable);

    /**
     * 聚合操作求和
     * @param spec 查询条件
     * @param field 要聚合的字段
     * @return 聚合结果列表
     */
    BigDecimal sum(Specification<T> spec, String field);

    /**
     * 聚合操作求和
     * @param spec 查询条件
     * @param fields 要聚合的字段列表
     * @return 聚合结果列表
     */
    List<BigDecimal> multiSum(Specification<T> spec, String... fields);

    /**
     * 聚合操作求和
     * @param spec 查询条件
     * @param fieldList 要聚合的字段集合
     * @return 聚合结果列表
     */
    List<BigDecimal> multiSum(Specification<T> spec, List<String> fieldList);
}