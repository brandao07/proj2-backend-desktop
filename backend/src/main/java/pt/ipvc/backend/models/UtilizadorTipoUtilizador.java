package pt.ipvc.backend.models;

import javax.persistence.Column;
import java.sql.Date;

public class UtilizadorTipoUtilizador {
        private Long id;

        private String tipo;

        public UtilizadorTipoUtilizador(Long id, String tipo) {
                this.id = id;
                this.tipo = tipo;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTipo() {
                return tipo;
        }

        public void setTipo(String tipo) {
                this.tipo = tipo;
        }

        @Override
        public String toString() {
                return "UtilizadorTipoUtilizador{" +
                        "id='" + id  +
                        ",tipo='" + tipo + '\'' +
                        '}';
        }
}
