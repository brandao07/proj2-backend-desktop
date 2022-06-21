open module pt.ipvc.backend {
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires annotations;
    requires org.apache.commons.lang3;
    requires commons.csv;

    exports pt.ipvc.backend.data.db.entity;
    exports pt.ipvc.backend.services;
    exports pt.ipvc.backend.data.db.repository;
    exports pt.ipvc.backend.data.db.entity.users;
    exports pt.ipvc.backend.data.db.repository.users;
    exports pt.ipvc.backend.services.util;
    exports pt.ipvc.backend.data.misc;
    exports pt.ipvc.backend.services.validations;
    exports pt.ipvc.backend.services.users;
    exports pt.ipvc.backend.models;
}