package com.springcourse.learnspring.beanlifecycle.remainingsteps;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanPostProcessorTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        // Load AlbumService Object
        AlbumService album = (AlbumService) applicationContext.getBean("albumService");
        System.out.println("Album Name : " + album.getAlbum().getAlbumName());

        applicationContext.registerShutdownHook();

    }
}
