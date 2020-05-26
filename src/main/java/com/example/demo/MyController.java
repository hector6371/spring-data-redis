package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class MyController {


    final Logger logger = Logger.getLogger("my-controller-logger");

    final MyEntityRepository myEntityRepository;

    public MyController(MyEntityRepository myEntityRepository) {
        this.myEntityRepository = myEntityRepository;
    }

    @GetMapping( "/my-endpoint")
    public ResponseEntity<?> myEndpoint () {

        MyEntity myEntity = new MyEntity();
        myEntity.setAttribute1(0.7);
        myEntity.setAttribute2("attr2");
        myEntity.setAttribute3("attr3");
        myEntity.setId1(1);

        myEntityRepository.save(myEntity);//create it in redis

        logger.info("STARTED");

        for (int i = 0; i < 100; i++){
            new Thread(){
                @Override
                public void run() {
                    super.run();

                    myEntity.setAttribute1(Math.random());

                    myEntityRepository.save(myEntity); //updating the entity

                    Optional<MyEntity> optionalMyEntity = myEntityRepository.findById(1);
                    if (optionalMyEntity.isPresent()) {
                        logger.info("found");
                    }else{
                        logger.warning("NOT FOUND");
                    }
                }
            }.start();

        }

        return ResponseEntity.noContent().build();
    }

}
