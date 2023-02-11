package com.lawman.inaction.advertisement.service;

import com.lawman.inaction.advertisement.dto.AdvertisementDto;
import com.lawman.inaction.advertisement.dto.CreateAdvertisementRequest;
import com.lawman.inaction.advertisement.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest request) {





        return null;

    }
}
