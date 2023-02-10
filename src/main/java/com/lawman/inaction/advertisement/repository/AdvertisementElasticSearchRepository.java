package com.lawman.inaction.advertisement.repository;

import com.lawman.inaction.advertisement.model.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdvertisementElasticSearchRepository  extends ElasticsearchRepository<Advertisement,String> {

    @Query("{\"match\": {\"message\": \"?0\"}}")
    Page<Advertisement> findByTitle(String title, Pageable pageable);
}
