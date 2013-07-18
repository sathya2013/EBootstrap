package com.me.bootstrap.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.me.bootstrap.entity.RolePermission;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRolePermission is a Querydsl query type for RolePermission
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRolePermission extends EntityPathBase<RolePermission> {

    private static final long serialVersionUID = -399149017;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRolePermission rolePermission = new QRolePermission("rolePermission");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPermission permission;

    public final QRole role;

    public QRolePermission(String variable) {
        this(RolePermission.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QRolePermission(Path<? extends RolePermission> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRolePermission(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRolePermission(PathMetadata<?> metadata, PathInits inits) {
        this(RolePermission.class, metadata, inits);
    }

    public QRolePermission(Class<? extends RolePermission> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.permission = inits.isInitialized("permission") ? new QPermission(forProperty("permission"), inits.get("permission")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}

