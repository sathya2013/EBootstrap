package com.me.bootstrap.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.me.bootstrap.entity.Role;
import com.me.bootstrap.entity.RolePermission;
import com.me.bootstrap.entity.UserRole;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = -734938056;

    public static final QRole role = new QRole("role");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<String, StringPath> permissionList = this.<String, StringPath>createList("permissionList", String.class, StringPath.class, PathInits.DIRECT2);

    public final SetPath<RolePermission, QRolePermission> rolePermissions = this.<RolePermission, QRolePermission>createSet("rolePermissions", RolePermission.class, QRolePermission.class, PathInits.DIRECT2);

    public final SetPath<UserRole, QUserRole> userRoles = this.<UserRole, QUserRole>createSet("userRoles", UserRole.class, QUserRole.class, PathInits.DIRECT2);

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QRole(Path<? extends Role> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata<?> metadata) {
        super(Role.class, metadata);
    }

}

