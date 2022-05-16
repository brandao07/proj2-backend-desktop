open module pt.ipvc.backend {
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;

    exports pt.ipvc.backend.entity;
    exports pt.ipvc.backend.bll;
}