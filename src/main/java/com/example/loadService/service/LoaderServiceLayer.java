package com.example.loadService.service;

import com.example.loadService.model.LoadService;
import com.example.loadService.repo.LoaderServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaderServiceLayer {
    @Autowired
    private LoaderServiceRepo loaderServiceRepo;
    public void createLoadData(LoadService loadService) {
        loaderServiceRepo.save(loadService);
    }

    public LoadService getById(Long loadId) {
        return loaderServiceRepo.findById(loadId).orElse(null);
    }

    public LoadService getLoadByShipperId(String shipperId) {
        return loaderServiceRepo.findByShipperId(shipperId);
    }


    public boolean updateLoad(Long loadId, LoadService loadService) {
        LoadService currentLoadService = loaderServiceRepo.findById(loadId).orElse(null);
        if(currentLoadService == null) {
            return false;
        }
        else  {
            currentLoadService.setLoadingPoint(loadService.getLoadingPoint());
            currentLoadService.setUnloadingPoint(loadService.getUnloadingPoint());
            currentLoadService.setProductType(loadService.getProductType());
            currentLoadService.setTruckType(loadService.getTruckType());
            currentLoadService.setNoOfTrucks(loadService.getNoOfTrucks());
            currentLoadService.setWeight(loadService.getWeight());
            currentLoadService.setComment(loadService.getComment());
            currentLoadService.setDate(loadService.getDate());

            loaderServiceRepo.save(currentLoadService);
            return true;
        }
    }

    public boolean deleteLoad(Long loadId) {
        if(!loaderServiceRepo.existsById(loadId)) {
            return false;
        }
        loaderServiceRepo.deleteById(loadId);
        return true;
    }
}
