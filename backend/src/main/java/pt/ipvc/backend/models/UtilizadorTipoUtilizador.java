package pt.ipvc.backend.models;

import javax.persistence.Column;
import java.sql.Date;

public class UtilizadorTipoUtilizador {
        private String username;

        private String email;

        private Date dataCriacao;

        private String tipo;


        public UtilizadorTipoUtilizador(String username, String email, Date dataCriacao, String tipo) {
                this.username = username;
                this.email = email;
                this.dataCriacao = dataCriacao;
                this.tipo = tipo;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public Date getDataCriacao() {
                return dataCriacao;
        }

        public void setDataCriacao(Date dataCriacao) {
                this.dataCriacao = dataCriacao;
        }

        public String getTipo() {
                return tipo;
        }

        public void setTipo(String tipo) {
                this.tipo = tipo;
        }

        @Override
        public String toString(){
                return "UtilizadorTipoUtilizador{"+
                        "username='" + username + '\'' +
                        ",email='" + email + '\'' +
                        ",tipo='" + tipo +
                        '}';
        }
}
