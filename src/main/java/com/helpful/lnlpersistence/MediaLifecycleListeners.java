package com.helpful.lnlpersistence;

import javax.persistence.*;

public class MediaLifecycleListeners {
    @PrePersist
    public void prePersist(Media media) {
        System.out.println("### PrePersist "+ media);
    }

    @PreRemove
    public void preRemove(Media media) {
        System.out.println("### PreRemove "+ media);
    }

    @PreUpdate
    public void preUpdate(Media media) {
        System.out.println("### PreUpdate "+ media);
    }

    @PostLoad
    public void postLoad(Media media) {
        System.out.println("### PostLoad "+ media);
    }

    @PostPersist
    public void postPersist(Media media) {
        System.out.println("### PostPersist "+ media);
    }

    @PostRemove
    public void postRemove(Media media) {
        System.out.println("### PostRemove "+ media);
    }

    @PostUpdate
    public void postUpdate(Media media) {
        System.out.println("### PostUpdate "+ media);
    }
}
