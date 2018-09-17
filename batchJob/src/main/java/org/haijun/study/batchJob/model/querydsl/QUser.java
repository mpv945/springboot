package org.haijun.study.batchJob.model.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import org.haijun.study.batchJob.model.User;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1358070583L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final StringPath id = createString("id");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final DatePath<java.time.LocalDate> lastModifiedDate = createDate("lastModifiedDate", java.time.LocalDate.class);

    public final StringPath mail = createString("mail");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

