package com.me.bootstrap.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.me.bootstrap.entity.Permission;
import com.me.bootstrap.entity.RolePermission;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPermission is a Querydsl query type for Permission
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPermission extends EntityPathBase<Permission> {

    private static final long serialVersionUID = -297621487;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPermission permission = new QPermission("permission");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QModule module;

    public final StringPath name = createString("name");

    public final SetPath<RolePermission, QRolePermission> rolePermissions = this.<RolePermission, QRolePermission>createSet("rolePermissions", RolePermission.class, QRolePermission.class, PathInits.DIRECT2);

    public final StringPath shortName = createString("shortName");

    public QPermission(String variable) {
        this(Permission.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QPermission(Path<? extends Permission> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPermission(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPermission(PathMetadata<?> metadata, PathInits inits) {
        this(Permission.class, metadata, inits);
    }

    public QPermission(Class<? extends Permission> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.module = inits.isInitialized("module") ? new QModule(forProperty("module"), inits.get("module")) : null;
    }

}

