package com.me.bootstrap.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.me.bootstrap.entity.OrgRole;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrgRole is a Querydsl query type for OrgRole
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrgRole extends EntityPathBase<OrgRole> {

    private static final long serialVersionUID = -1378048392;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrgRole orgRole = new QOrgRole("orgRole");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOrgnization orgnization;

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public final NumberPath<java.math.BigInteger> roleId = createNumber("roleId", java.math.BigInteger.class);

    public QOrgRole(String variable) {
        this(OrgRole.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QOrgRole(Path<? extends OrgRole> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrgRole(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrgRole(PathMetadata<?> metadata, PathInits inits) {
        this(OrgRole.class, metadata, inits);
    }

    public QOrgRole(Class<? extends OrgRole> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orgnization = inits.isInitialized("orgnization") ? new QOrgnization(forProperty("orgnization"), inits.get("orgnization")) : null;
    }

}

