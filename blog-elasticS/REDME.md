---------------------- 2019.10.16 更新 --------------------------
# springboot 集成es

- springboot默认支持两种交互技术

  1. jest(默认不生效):需要jest工具包才能使用

  2. SpringData ElasticSearch(es版本有可能不合适)

      (1). Client节点：cluster.Nodes,cluster.name

      (2). ElasticsearchTemplate

      (3). 启用ElasticsearchRepository和ElasticsearchCrudRepository接口
      

```java
@NoRepositoryBean
public interface ElasticsearchRepository<T, ID extends Serializable> extends ElasticsearchCrudRepository<T, ID> {
    <S extends T> S index(S var1);

    Iterable<T> search(QueryBuilder var1);

    Page<T> search(QueryBuilder var1, Pageable var2);

    Page<T> search(SearchQuery var1);

    Page<T> searchSimilar(T var1, String[] var2, Pageable var3);

    void refresh();

    Class<T> getEntityClass();
}
```

- springboot 通过jest来操作es：
```yml
spring:
  elasticsearch:
    jest:
      uris: http://192.168.1.70:9200/
```

