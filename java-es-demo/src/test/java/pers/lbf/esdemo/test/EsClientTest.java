package pers.lbf.esdemo.test;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;

/**
 * @author ferryman
 * @since 2022/5/26 16:34
 */
public class EsClientTest {


    static final RestHighLevelClient client;

    static {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("es", 9200)
                )
        );

        System.out.println("客户端初始化成功");
    }


    @After
    public void after() throws IOException {
        client.close();
    }


    @Test
    public void testSearch() throws IOException {

        // 构建查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.must(QueryBuilders.termQuery("OriginCountry", "CN"));

//        boolQueryBuilder.must(QueryBuilders.termQuery("DestCountry", "CN"));

        // 构建原生查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        // 分页，起始0行
        int fromRecord = 742;
        searchSourceBuilder.from(fromRecord);
        // 分页 每页大小
        int size = 10;
        searchSourceBuilder.size(size);
        System.out.println(fromRecord / size);
        int page = fromRecord / size + 1;
        // 排序字段
        searchSourceBuilder.sort("FlightTimeHour", SortOrder.DESC);

        // 构建查询请求
        SearchRequest searchRequest = new SearchRequest("kibana_sample_data_flights");

        searchRequest.source(searchSourceBuilder);


        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 总行数
        TotalHits totalHits = searchResponse.getHits().getTotalHits();
        System.out.printf("总行数%d 当前页%d 每页大小%d\n", totalHits.value, page, size);

        System.out.printf("当前获取到的记录数%d", searchResponse.getHits().getHits().length);

        if (searchResponse.getHits().getHits().length > 0) {
            System.out.println(searchResponse.getHits().getHits()[0]);
        }
    }


}
