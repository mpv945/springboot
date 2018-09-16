package org.haijun.study.batchJob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Wither;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_users")
//lombok 说明https://www.jianshu.com/p/365ea41b3573
@Getter(value = AccessLevel.PUBLIC)@Setter

//主要用于控制生成的getter和setter（参数 prefix 设置前缀 例如：@Accessors(prefix = "abc") private String abcAge 当生成get/set方法时，会把此前缀去掉）
//@Accessors//（主要参数：fluent boolean值，是否不需要生成带以get/set开头的方法；chain boolean值，是否setter返回此对象，方便链式调用方法；
//如果继承的有父类的话，可以设置callSuper 让其调用父类的toString()方法，@ToString(callSuper = true)
//@ToString(exclude = {"id"})
//生成hashCode()和equals()方法，默认情况下，它将使用所有非静态，非transient字段。可选的exclude参数中来排除更多字段,或者，通过在parameter参数中命名它们来准确指定希望使用哪些字段。
//@EqualsAndHashCode
//生成一个无参构造方法。当类中有final字段没有被初始化时，编译器会报错，此时可用@NoArgsConstructor(force = true)，然后就会为没有初始化的final字段设置默认值 0 / false / null。
//@NoArgsConstructor //对于具有约束的字段（例如@NonNull字段），不会生成检查或分配，因此请注意，正确初始化这些字段之前，这些约束无效。
//会生成构造方法（可能带参数也可能不带参数），如果带参数，这参数只能是以final修饰的未经初始化的字段，或者是以@NonNull注解的未经初始化的字段
//@RequiredArgsConstructor(staticName = "of")//会生成一个of()的静态方法，并把构造方法设置为私有的
//@AllArgsConstructor// 生成一个全参数的构造方法
//@Data//@Data 包含了 @ToString、@EqualsAndHashCode、@Getter / @Setter和@RequiredArgsConstructor的功能
//@Synchronized //给方法加上同步锁
//@Wither(AccessLevel.PROTECTED) @NonNull private final String name; 会生成protected 当前类对象 withName(@NonNull String name)
public class User {// implements Serializable


    //主键
    @Id
    private String id;
    //用户名
    @Column(name = "user_name",columnDefinition = "varchar(15) not null comment '用户名'")
    private String name;
    //密码
    @Column(name = "password",columnDefinition = "varchar(256) not null comment '密码'")
    private String password;
    //邮箱
    @Column(name = "mail",columnDefinition = "varchar(126) comment '邮箱'")
    private String mail;
    // 创建时间
    //@JsonIgnore
    //@CreatedDate 审计字段
    @Column(name = "create_date",columnDefinition = "datetime(6) comment '创建时间'")
    private LocalDateTime createDate;

    // 最后更新时间
    //@JsonIgnore
    //@LastModifiedDate
    @Column(name = "last_modified_date",columnDefinition = "date comment '最后修改时间时间'")
    private LocalDate lastModifiedDate;

    // 参考https://www.cnblogs.com/sweetchildomine/p/7729319.html
    /*@LastModifiedBy
    private String lastModifiedBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;*/

    @Column(name = "is_deleted",columnDefinition = "tinyint comment '是否删除：0未删除状态，1删除状态'")
    private boolean isDeleted;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        //默认插入的数据，删除标记为0
        this.isDeleted=false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = LocalDate.now();
        //this.isDeleted="0";
    }

}
