package com.lawman.inaction.advertisement.repository;

import com.lawman.inaction.advertisement.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, String> {


}
