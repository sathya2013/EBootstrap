package com.me.bootstrap.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.me.bootstrap.entity.Logs;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLogs is a Querydsl query type for Logs
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLogs extends EntityPathBase<Logs> {

    private static final long serialVersionUID = -735116943;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogs logs = new QLogs("logs");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ipAddress = createString("ipAddress");

    public final StringPath logLevel = createString("logLevel");

    public final StringPath message = createString("message");

    public final QUser user;

    public QLogs(String variable) {
        this(Logs.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QLogs(Path<? extends Logs> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLogs(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLogs(PathMetadata<?> metadata, PathInits inits) {
        this(Logs.class, metadata, inits);
    }

    public QLogs(Class<? extends Logs> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

