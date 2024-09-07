package com.example.loadService.controller;

import com.example.loadService.model.LoadService;
import com.example.loadService.service.LoaderServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/load")
public class LoaderServiceController {
    @Autowired
    private LoaderServiceLayer loaderServiceLayer;
    @PostMapping
    public ResponseEntity<String> createLoadServiceData(@RequestBody LoadService loadService) {
        try {
            loaderServiceLayer.createLoadData(loadService);
            return ResponseEntity.status(HttpStatus.CREATED).body("load details added sucessfully");
        }
        catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("SomeThing went wrong while creating load service data");
        }
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<LoadService> getLoadById(@PathVariable Long loadId) {
        LoadService loadService = loaderServiceLayer.getById(loadId);
        if(loadService == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(loadService);
    }

    @GetMapping("/detail/{shipperId}")
    public ResponseEntity<LoadService> getLoadsByShipperId(@PathVariable  String shipperId) {
      LoadService loads = loaderServiceLayer.getLoadByShipperId(shipperId);
      if(loads == null) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
      return  ResponseEntity.ok(loads);
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<String> updateLoadData(@PathVariable Long loadId ,@RequestBody LoadService loadService) {
       boolean isLoadupdated = loaderServiceLayer.updateLoad(loadId , loadService);
       if(!isLoadupdated) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Load not found");
       }
       return ResponseEntity.ok("load updated successfully");
    }


    @DeleteMapping("/{loadId}")
    public ResponseEntity<String> deleteloadById(@PathVariable Long loadId) {
        boolean deletedLoad = loaderServiceLayer.deleteLoad(loadId);
        if(!deletedLoad) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("something went wrong");
        }

        return  ResponseEntity.status(HttpStatus.OK).body("successfully deleted");
    }
}

