package com.me.bootstrap.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.me.bootstrap.entity.Module;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QModule is a Querydsl query type for Module
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QModule extends EntityPathBase<Module> {

    private static final long serialVersionUID = -2044200530;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QModule module = new QModule("module");

    public final SetPath<Module, QModule> children = this.<Module, QModule>createSet("children", Module.class, QModule.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QModule parent;

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public final StringPath sn = createString("sn");

    public final StringPath url = createString("url");

    public QModule(String variable) {
        this(Module.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QModule(Path<? extends Module> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QModule(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QModule(PathMetadata<?> metadata, PathInits inits) {
        this(Module.class, metadata, inits);
    }

    public QModule(Class<? extends Module> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QModule(forProperty("parent"), inits.get("parent")) : null;
    }

}

