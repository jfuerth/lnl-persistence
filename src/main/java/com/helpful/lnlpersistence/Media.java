package com.helpful.lnlpersistence;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@EntityListeners(MediaLifecycleListeners.class)
public class Media {

    @Id
    private String id;

    private String url;
    private Instant createDate;
}
