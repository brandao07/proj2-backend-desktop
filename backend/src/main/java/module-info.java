open module pt.ipvc.backend {
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires annotations;
    requires org.apache.commons.lang3;
    requires commons.csv;

    exports pt.ipvc.backend.entity;
    exports pt.ipvc.backend.bll;
    exports pt.ipvc.backend.servicos;
}