package com.gym.crm;

import com.gym.crm.config.StorageConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GymCRM {
    public static void main(String[] args) {
      var applicationContext = new AnnotationConfigApplicationContext(StorageConfiguration.class);
      applicationContext.getBean(StorageConfiguration.TRAINEE_DB_BEAN_NAME);
    }
}
