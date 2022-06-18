package com.example.securitytoken.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
//на основании ролей определяем имеет тот ли иной пользователь доступ к тем или иным эндпоинтам
public class Role extends CommonEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roleList")
    private List<User> userList;
}
