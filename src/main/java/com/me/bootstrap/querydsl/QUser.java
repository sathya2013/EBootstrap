package com.me.bootstrap.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.me.bootstrap.entity.Logs;
import com.me.bootstrap.entity.User;
import com.me.bootstrap.entity.UserRole;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -734845043;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<Logs, QLogs> logs = this.<Logs, QLogs>createSet("logs", Logs.class, QLogs.class, PathInits.DIRECT2);

    public final QOrgnization orgnization;

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath picture = createString("picture");

    public final StringPath realname = createString("realname");

    public final StringPath salt = createString("salt");

    public final StringPath status = createString("status");

    public final StringPath username = createString("username");

    public final SetPath<UserRole, QUserRole> userRoles = this.<UserRole, QUserRole>createSet("userRoles", UserRole.class, QUserRole.class, PathInits.DIRECT2);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QUser(Path<? extends User> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orgnization = inits.isInitialized("orgnization") ? new QOrgnization(forProperty("orgnization"), inits.get("orgnization")) : null;
    }

}

