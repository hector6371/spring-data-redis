package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        MyEntity myEntity = createEntity();
        myEntityRepository.save(myEntity);//save it for the first time after creation

        logger.info("STARTED");

        for (int i = 0; i < 100; i++){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    myEntity.setAttribute1("attr1-modified");
                    myEntityRepository.save(myEntity); //updating a value in redis

                    Optional<MyEntity> optionalMyEntity = myEntityRepository.findByIndex1AndIndex2(1, 2);
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

    private MyEntity createEntity(){
        MyEntity myEntity = new MyEntity();
        myEntity.setAttribute1("attr1");
        myEntity.setAttribute2("attr2");
        myEntity.setAttribute3("attr3");
        myEntity.setAttribute4("attr4");
        myEntity.setAttribute5("attr5");
        myEntity.setAttribute6("attr6");
        myEntity.setAttribute7("attr7");
        myEntity.setAttribute8("attr8");
        myEntity.setAttribute9("attr9");
        myEntity.setAttribute10("attr10");
        myEntity.setAttribute11("attr11");
        myEntity.setAttribute12("attr12");

        myEntity.setIndex1(1);
        myEntity.setIndex2(2);
        myEntity.setIndex3(3);
        myEntity.setIndex4("4");

        myEntity.setId1(1);
        return myEntity;
    }
}
