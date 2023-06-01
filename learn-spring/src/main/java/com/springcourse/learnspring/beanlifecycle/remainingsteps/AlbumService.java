package com.springcourse.learnspring.beanlifecycle.remainingsteps;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class AlbumService implements InitializingBean, DisposableBean {

    private Album album;

    public AlbumService() {
        System.out.println("AlbumService no-arg constructor");
    }

    @Override
    public void afterPropertiesSet() { // init-method
        if (!album.getAlbumName().equals("Paradise")) {
            album.setAlbumName("ConquestOfParadise");
        }
        System.out.println("AlbumService afterPropertiesSet method");
    }

    @PostConstruct
    public void Initialize() {
        System.out.println("Initializing the bean");
    }

    @PreDestroy
    public void cleanUp() {

        System.out.println("Cleaning up the resources");
    }

    @Override
    public void destroy() { // destroy-method
        System.out.println("AlbumService destroy method - Closing Opened Resources");
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }



}
