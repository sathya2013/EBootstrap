package com.me.bootstrap.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.me.bootstrap.entity.Orgnization;
import com.me.bootstrap.entity.User;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrgnization is a Querydsl query type for Orgnization
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrgnization extends EntityPathBase<Orgnization> {

    private static final long serialVersionUID = -1059980008;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrgnization orgnization = new QOrgnization("orgnization");

    public final SetPath<Orgnization, QOrgnization> children = this.<Orgnization, QOrgnization>createSet("children", Orgnization.class, QOrgnization.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QOrgnization parent;

    public final SetPath<User, QUser> users = this.<User, QUser>createSet("users", User.class, QUser.class, PathInits.DIRECT2);

    public QOrgnization(String variable) {
        this(Orgnization.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QOrgnization(Path<? extends Orgnization> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrgnization(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrgnization(PathMetadata<?> metadata, PathInits inits) {
        this(Orgnization.class, metadata, inits);
    }

    public QOrgnization(Class<? extends Orgnization> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QOrgnization(forProperty("parent"), inits.get("parent")) : null;
    }

}

